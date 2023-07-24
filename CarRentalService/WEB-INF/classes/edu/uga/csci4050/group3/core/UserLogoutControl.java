package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.db.SessionManagement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutControl {
  public void logout(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    sessMan.invalidateSession();
  }
  
  public boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    return sessMan.isUserLoggedIn();
  }
}
