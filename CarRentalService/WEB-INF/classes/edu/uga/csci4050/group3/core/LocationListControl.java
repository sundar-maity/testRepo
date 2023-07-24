package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationListControl {
  public List<LocationEntity> getList() {
    try {
      List<LocationEntity> list = DatabaseAbstraction.getLocations();
      return list;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
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
  
  public boolean isCustomer(HttpServletRequest request, HttpServletResponse response) {
    SessionManagement sessMan = new SessionManagement(request, response);
    if (sessMan.isUserLoggedIn())
      try {
        if (sessMan.getUserRole() == UserType.CUSTOMER)
          return true; 
        return false;
      } catch (SessionException e) {
        return false;
      }  
    return false;
  }
}
