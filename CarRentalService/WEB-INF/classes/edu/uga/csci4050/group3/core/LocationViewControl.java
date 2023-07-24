package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionException;
import edu.uga.csci4050.group3.db.SessionManagement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationViewControl {
  public LocationEntity getLocation(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    LocationEntity loc = DatabaseAbstraction.getLocation(request.getParameter("uid"));
    return loc;
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
