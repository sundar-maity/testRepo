package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.PaymentReason;
import edu.uga.csci4050.group3.core.PaymentTransactionEntity;
import edu.uga.csci4050.group3.core.RentalStatus;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

public class ReservationReturnControl {
  public boolean isReturningPossible(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    String uid = request.getParameter("uid");
    RentalTransactionEntity rental = DatabaseAbstraction.getRentalTransaction(uid);
    if (rental.getStatusEnum() == RentalStatus.ACTIVE)
      return true; 
    return false;
  }
  
  public double getAdditionalCharges(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    String uid = request.getParameter("uid");
    RentalTransactionEntity rental = DatabaseAbstraction.getRentalTransaction(uid);
    if (rental.getEnd_date() >= DatabaseAbstraction.getTimestampFromDate(new Date()) && rental
      .getStart_date() <= DatabaseAbstraction.getTimestampFromDate(new Date()))
      return 0.0D; 
    VehicleEntity vehicle = DatabaseAbstraction.getVehicle(rental.getVehicle());
    VehicleTypeEntity vtype = DatabaseAbstraction.getVehicleType(vehicle.getType());
    Date current_time = new Date();
    long difference = current_time.getTime() - rental.getEnd_dateDate().getTime();
    if (rental.getStart_date() > DatabaseAbstraction.getTimestampFromDate(new Date()))
      return vtype.getHourly_rate().doubleValue(); 
    if (difference > 0L) {
      int hoursToCharge = (int)Math.ceil(TimeUnit.MILLISECONDS.toHours(difference));
      return vtype.getHourly_rate().doubleValue() * hoursToCharge + 50.0D;
    } 
    return 0.0D;
  }
  
  public void processReturn(HttpServletRequest request) throws InvalidUrlException, InvalidInputException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("rentalUID"))
      throw new InvalidUrlException(); 
    String uid = request.getParameter("rentalUID");
    RentalTransactionEntity rental = DatabaseAbstraction.getRentalTransaction(uid);
    double extraPayment = getAdditionalCharges(request);
    if (extraPayment > 0.0D) {
      if (!request.getParameterMap().containsKey("rentalCardNumber"))
        throw new InvalidUrlException(); 
      if (!request.getParameterMap().containsKey("rentalCSV"))
        throw new InvalidUrlException(); 
      String cardNumber = request.getParameter("rentalCardNumber");
      String cardCSV = request.getParameter("rentalCSV");
      InvalidInputException invalidEx = new InvalidInputException();
      String cardPattern = "[0-9]{13,19}|([0-9]{4,8}[-]{1}){3,5}[0-9]{4,8}";
      String csvPattern = "[0-9]{3,4}";
      PaymentTransactionEntity payment = new PaymentTransactionEntity();
      if (!cardNumber.matches(cardPattern))
        invalidEx.addMessage("Invalid card number format. Expected: XXXX-XXXX-XXXX-XXXX"); 
      if (!cardCSV.matches(csvPattern))
        invalidEx.addMessage("Invalid CSV code format. Expected: XXX or XXXX"); 
      if (invalidEx.countMessages() > 0)
        throw invalidEx; 
      payment.setDateDate(new Date());
      payment.setReasonFromEnum(PaymentReason.EXTRA);
      payment.setDescription("CSV: " + cardCSV);
      payment.setUser(rental.getUser());
      payment.setMethod("Card");
      DatabaseAbstraction.putPaymentTransaction(payment);
    } 
    rental.setStatusEnum(RentalStatus.RETURNED);
    if (!request.getParameterMap().containsKey("rentalComments")) {
      rental.setComments("No comments");
    } else {
      rental.setComments(request.getParameter("rentalComments"));
    } 
    DatabaseAbstraction.updateRentalTransaction(rental);
  }
}
