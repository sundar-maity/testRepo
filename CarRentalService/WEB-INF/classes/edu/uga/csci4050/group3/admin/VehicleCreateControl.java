package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Map;

public class VehicleCreateControl {
  public void create() {
    VehicleEntity vh = new VehicleEntity();
    DatabaseAbstraction.putVehicle(vh);
  }
  
  public void create(Map<String, String[]> map) throws InvalidInputException {
    VehicleEntity vh = new VehicleEntity();
    vh.loadFromForm(map);
    vh.validate();
    DatabaseAbstraction.putVehicle(vh);
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
}
