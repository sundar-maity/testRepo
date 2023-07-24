package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.core.LocationListControl;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate list = new SimpleTemplate(context, "LocationList.mustache");
    SimpleTemplate countryList = new SimpleTemplate(context, "CountrySelectInput.mustache");
    SimpleTemplate stateList = new SimpleTemplate(context, "StateSelectInput.mustache");
    SimpleTemplate filterForm = new SimpleTemplate(context, "CustomerLocationEntry.mustache");
    LocationListControl control = new LocationListControl();
    lr.setTitle("Locations");
    boolean isAdmin = control.isAdmin(request, response);
    if (isAdmin) {
      SimpleTemplate menu = new SimpleTemplate(context, "LocationListAdminMenu.mustache");
      String extraOptions = "";
      extraOptions = extraOptions + menu.render();
      countryList.setVariable("name", "country");
      filterForm.setVariable("select_country", countryList.render());
      stateList.setVariable("name", "state");
      filterForm.setVariable("select_state", stateList.render());
      extraOptions = extraOptions + filterForm.render();
      list.setVariable("extra_options", extraOptions);
    } else {
      countryList.setVariable("name", "country");
      filterForm.setVariable("select_country", countryList.render());
      stateList.setVariable("name", "state");
      filterForm.setVariable("select_state", stateList.render());
      list.setVariable("extra_options", filterForm.render());
    } 
    List<LocationEntity> locations = control.getList();
    if (locations.size() > 0) {
      String locationsHtml = "";
      for (LocationEntity loc : locations) {
        SimpleTemplate locRow = new SimpleTemplate(context, "LocationRow.mustache");
        locRow.setVariables(loc.getData());
        locationsHtml = locationsHtml + locRow.render();
      } 
      list.setVariable("locations", locationsHtml);
    } else {
      list.setVariable("message", "<h4>No locations found</h4>");
    } 
    lr.setContent(list.render());
    lr.render(response);
  }
}
