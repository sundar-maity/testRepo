package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleListControl {
  public List<VehicleEntity> getList(Map<String, String[]> map) {
    try {
      return DatabaseAbstraction.getVehicles();
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
}
