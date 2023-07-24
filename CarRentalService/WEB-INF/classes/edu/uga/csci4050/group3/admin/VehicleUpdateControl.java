package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class VehicleUpdateControl {
  public void updateVehicle(HttpServletRequest request) throws InvalidInputException, InvalidUrlException {
    VehicleEntity vehicle = new VehicleEntity();
    vehicle.loadFromForm(request.getParameterMap());
    Map<String, String[]> params = request.getParameterMap();
    if (!params.containsKey("uid"))
      throw new InvalidUrlException(); 
    vehicle.setUid(((String[])params.get("uid"))[0]);
    vehicle.validate();
    DatabaseAbstraction.updateVehicle(vehicle);
  }
  
  public boolean isDbPopulated() {
    try {
      DatabaseAbstraction.getLocations();
    } catch (RecordNotFoundException e) {
      return false;
    } 
    try {
      DatabaseAbstraction.getVehicleTypes();
    } catch (RecordNotFoundException e) {
      return false;
    } 
    return true;
  }
  
  public VehicleEntity getVehicle(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    return DatabaseAbstraction.getVehicle(request.getParameter("uid"));
  }
}
