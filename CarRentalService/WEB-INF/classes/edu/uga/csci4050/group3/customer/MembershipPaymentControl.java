package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.PaymentReason;
import edu.uga.csci4050.group3.core.PaymentTransactionEntity;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.db.Settings;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembershipPaymentControl {
  public boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    return sessMan.isUserLoggedIn();
  }
  
  public void extendMembership(HttpServletRequest request, HttpServletResponse response) throws InvalidUrlException, InvalidInputException, RecordNotFoundException, AuthenticationException {
    if (!request.getParameterMap().containsKey("membershipCardNumber"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("membershipCSV"))
      throw new InvalidUrlException(); 
    String cardNumber = request.getParameter("membershipCardNumber");
    String cardCSV = request.getParameter("membershipCSV");
    InvalidInputException invalidEx = new InvalidInputException();
    String cardPattern = "[0-9]{13,19}|([0-9]{4,8}[-]{1}){3,5}[0-9]{4,8}";
    String csvPattern = "[0-9]{3,4}";
    if (!cardNumber.matches(cardPattern))
      invalidEx.addMessage("Invalid card number format. Expected: XXXX-XXXX-XXXX-XXXX"); 
    if (!cardCSV.matches(csvPattern))
      invalidEx.addMessage("Invalid CSV code format. Expected: XXX or XXXX"); 
    if (invalidEx.countMessages() > 0)
      throw invalidEx; 
    SessionManagement sessMan = new SessionManagement(request, response);
    UserEntity user = DatabaseAbstraction.getUserByUsername(sessMan.getLoggedinUsername());
    Date currentTime = new Date();
    if (user.getMembershipExpirationDate().getTime() > currentTime.getTime())
      if (TimeUnit.MILLISECONDS.toDays(user.getMembershipExpirationDate().getTime() - currentTime.getTime()) > 180L) {
        invalidEx.addMessage("Your membership is already at the maximum extension. You have to wait until it goes below 6 months for an extension.");
        throw invalidEx;
      }  
    long newTime = 0L;
    if (user.getMembershipExpirationDate().getTime() < currentTime.getTime()) {
      newTime = currentTime.getTime() + TimeUnit.DAYS.toMillis(180L);
    } else {
      newTime = user.getMembershipExpirationDate().getTime() + TimeUnit.DAYS.toMillis(180L);
    } 
    PaymentTransactionEntity payment = new PaymentTransactionEntity();
    payment.setDateDate(new Date());
    payment.setReasonFromEnum(PaymentReason.MEMBERSHIP);
    payment.setDescription("CSV: " + cardCSV);
    payment.setUser(user.getUid());
    payment.setMethod("Card");
    DatabaseAbstraction.putPaymentTransaction(payment);
    user.setMembershipExpirationDate(new Date(newTime));
    DatabaseAbstraction.updateUser(user);
  }
  
  public double getMembershipFee(ServletContext context) {
    Settings settings = Settings.loadFromStorage(context);
    double value = settings.getMembershipFee();
    return value;
  }
  
  public Date getExpirationDate(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, RecordNotFoundException {
    String username = (new SessionManagement(request, response)).getLoggedinUsername();
    UserEntity user = DatabaseAbstraction.getUserByUsername(username);
    return user.getMembershipExpirationDate();
  }
  
  public String getExpirationDateString(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, RecordNotFoundException {
    SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    return sdftime.format(getExpirationDate(request, response));
  }
}
