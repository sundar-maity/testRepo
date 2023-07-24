package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import javax.servlet.http.HttpServletRequest;

public class VehicleTypeCreateControl {
  public void create(HttpServletRequest request) throws InvalidInputException {
    VehicleTypeEntity type = new VehicleTypeEntity();
    type.loadFromForm(request.getParameterMap());
    type.validate();
    DatabaseAbstraction.putVehicleType(type);
  }
}
