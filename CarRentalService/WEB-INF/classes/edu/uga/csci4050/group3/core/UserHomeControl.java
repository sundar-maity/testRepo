package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionException;
import edu.uga.csci4050.group3.db.SessionManagement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserHomeControl {
  public boolean isLoggedIn(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    return sessMan.isUserLoggedIn();
  }
  
  public String getRoleString(HttpServletRequest request, HttpServletResponse response) {
    String role;
    SessionManagement sessMan = new SessionManagement(request, response);
    try {
      role = sessMan.getUserRole().toString();
    } catch (SessionException e) {
      role = "Unknown";
    } 
    return role;
  }
  
  public boolean isAdmin(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    if (sessMan.isUserLoggedIn())
      try {
        if (sessMan.getUserRole() == UserType.ADMIN)
          return true; 
        return false;
      } catch (SessionException e) {
        return false;
      }  
    return false;
  }
}
