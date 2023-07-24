package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public class LocationFilterControl {
  public List<LocationEntity> getFilteredList(HttpServletRequest request) {
    try {
      List<LocationEntity> list = DatabaseAbstraction.getLocations();
      List<LocationEntity> filteredList = new ArrayList<>();
      for (LocationEntity loc : list) {
        boolean matches = true;
        if (request.getParameterMap().containsKey("country") && request.getParameter("country") != "" && 
          !loc.getCountry().equals(request.getParameter("country")))
          matches = false; 
        if (request.getParameterMap().containsKey("city") && request.getParameter("city") != "" && 
          !loc.getCity().equals(request.getParameter("city")))
          matches = false; 
        if (request.getParameterMap().containsKey("state") && request.getParameter("state") != "" && 
          !loc.getState().equals(request.getParameter("state")))
          matches = false; 
        System.out.println("zipcode-------" + request.getParameter("zipcode"));
        if (request.getParameterMap().containsKey("zipcode") && request.getParameter("zipcode") != null && !request.getParameter("zipcode").isEmpty() && 
          loc.getZipcode() != (new Integer(request.getParameter("zipcode"))).intValue())
          matches = false; 
        if (matches == true)
          filteredList.add(loc); 
      } 
      return filteredList;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
}
