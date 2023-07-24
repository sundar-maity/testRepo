package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.PaymentReason;
import edu.uga.csci4050.group3.core.PaymentTransactionEntity;
import edu.uga.csci4050.group3.core.RentalStatus;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleRentConfirmControl {
  public void confirmRental(HttpServletRequest request, HttpServletResponse response) throws InvalidInputException, InvalidUrlException, RecordNotFoundException, AuthenticationException {
    Date start_date = new Date();
    Date end_date = new Date();
    if (!request.getParameterMap().containsKey("rentalVehicleUID"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalStartDate"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalEndDate"))
      throw new InvalidUrlException(); 
    InvalidInputException invalidEx = new InvalidInputException();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    try {
      start_date = sdf.parse(request.getParameter("rentalStartDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid start date format");
    } 
    try {
      end_date = sdf.parse(request.getParameter("rentalEndDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid end date format");
    } 
    if (!request.getParameterMap().containsKey("rentalCardNumber"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalCSV"))
      throw new InvalidUrlException(); 
    String cardNumber = request.getParameter("rentalCardNumber");
    String cardCSV = request.getParameter("rentalCSV");
    String cardPattern = "[0-9]{13,19}|([0-9]{4,8}[-]{1}){3,5}[0-9]{4,8}";
    String csvPattern = "[0-9]{3,4}";
    if (!cardNumber.matches(cardPattern))
      invalidEx.addMessage("Invalid card number format. Expected: XXXX-XXXX-XXXX-XXXX"); 
    if (!cardCSV.matches(csvPattern))
      invalidEx.addMessage("Invalid CSV code format. Expected: XXX or XXXX"); 
    if (invalidEx.countMessages() > 0)
      throw invalidEx; 
    VehicleEntity vehicle = DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    VehicleTypeEntity vtype = DatabaseAbstraction.getVehicleType(vehicle.getType());
    UserEntity user = DatabaseAbstraction.getUserByUsername((new SessionManagement(request, response)).getLoggedinUsername());
    Date currentTime = new Date();
    if (currentTime.getTime() > user.getMembershipExpirationDate().getTime())
      throw new InvalidInputException("User membership has expired or hasn't been created. Unable to continue"); 
    boolean isVehicleAvailable = false;
    try {
      List<RentalTransactionEntity> conflicts = DatabaseAbstraction.getConflictingRentalTransactions(vehicle.getUid(), start_date, end_date);
      isVehicleAvailable = false;
    } catch (RecordNotFoundException ex) {
      isVehicleAvailable = true;
    } 
    double amountToPay = 0.0D;
    long diff = end_date.getTime() - start_date.getTime();
    if (diff <= 0L)
      throw new InvalidInputException(); 
    if (TimeUnit.MILLISECONDS.toHours(diff) >= 24L) {
      amountToPay = vtype.getDaily_rate().doubleValue() * Math.ceil(TimeUnit.MILLISECONDS.toDays(diff));
    } else {
      amountToPay = vtype.getHourly_rate().doubleValue() * TimeUnit.MILLISECONDS.toHours(diff);
    } 
    PaymentTransactionEntity payment = new PaymentTransactionEntity();
    payment.setDateDate(new Date());
    payment.setReasonFromEnum(PaymentReason.RENTAL);
    payment.setDescription("CSV: " + cardCSV);
    payment.setUser(user.getUid());
    payment.setMethod("Card");
    DatabaseAbstraction.putPaymentTransaction(payment);
    RentalTransactionEntity rental = new RentalTransactionEntity();
    rental.setStart_dateDate(start_date);
    rental.setEnd_dateDate(end_date);
    rental.setVehicle(vehicle.getUid());
    rental.setUser(user.getUid());
    rental.setStatusEnum(RentalStatus.ACTIVE);
    DatabaseAbstraction.putRentalTransaction(rental);
  }
}
