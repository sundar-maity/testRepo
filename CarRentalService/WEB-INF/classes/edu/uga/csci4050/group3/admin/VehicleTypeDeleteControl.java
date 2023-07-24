package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class VehicleTypeDeleteControl {
  public void delete(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    Map<String, String[]> params = request.getParameterMap();
    if (!params.containsKey("uid"))
      throw new InvalidUrlException(); 
    String uid = ((String[])params.get("uid"))[0];
    DatabaseAbstraction.deleteVehicleType(uid);
  }
}
