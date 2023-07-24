package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import javax.servlet.http.HttpServletRequest;

public class LocationCreateControl {
  public void create(HttpServletRequest request) throws InvalidInputException {
    LocationEntity location = new LocationEntity();
    location.loadFromForm(request.getParameterMap());
    location.validate();
    DatabaseAbstraction.putLocation(location);
  }
}
