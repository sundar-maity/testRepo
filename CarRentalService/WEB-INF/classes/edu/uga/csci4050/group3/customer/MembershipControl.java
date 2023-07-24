package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembershipControl {
  public boolean customerHasMembership(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, RecordNotFoundException {
    Date currTime = new Date();
    if (isExpired(request, response))
      return false; 
    return true;
  }
  
  public boolean isExpired(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, RecordNotFoundException {
    Date currTime = new Date();
    if (getExpirationDate(request, response).getTime() < currTime.getTime())
      return true; 
    return false;
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
