package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class LocationUpdateControl {
  public void update(HttpServletRequest request) throws InvalidUrlException, InvalidInputException {
    LocationEntity location = new LocationEntity();
    location.loadFromForm(request.getParameterMap());
    Map<String, String[]> params = request.getParameterMap();
    if (!params.containsKey("uid"))
      throw new InvalidUrlException(); 
    location.setUid(((String[])params.get("uid"))[0]);
    location.validate();
    DatabaseAbstraction.updateLocation(location);
  }
  
  public LocationEntity getLocation(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    return DatabaseAbstraction.getLocation(request.getParameter("uid"));
  }
}
