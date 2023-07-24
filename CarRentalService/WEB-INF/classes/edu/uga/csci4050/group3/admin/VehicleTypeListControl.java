package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class VehicleTypeListControl {
  public List<VehicleTypeEntity> list() {
    try {
      List<VehicleTypeEntity> types = DatabaseAbstraction.getVehicleTypes();
      return types;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
  
  public int count() {
    try {
      List<VehicleTypeEntity> types = DatabaseAbstraction.getVehicleTypes();
      return types.size();
    } catch (RecordNotFoundException e) {
      return 0;
    } 
  }
}
