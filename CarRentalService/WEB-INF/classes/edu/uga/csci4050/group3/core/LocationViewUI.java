package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.core.LocationViewControl;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleListControl;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationViewUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate view = new SimpleTemplate(context, "LocationView.mustache");
    LocationViewControl control = new LocationViewControl();
    lr.setTitle("Location details");
    try {
      LocationEntity loc = control.getLocation(request);
      view.setVariables(loc.getData());
      boolean isAdmin = control.isAdmin(request, response);
      if (isAdmin) {
        SimpleTemplate menu = new SimpleTemplate(context, "LocationViewAdminMenu.mustache");
        menu.setVariable("uid", loc.getUid());
        view.setVariable("extra_options", menu.render());
      } 
      SimpleTemplate cardTemplate = new SimpleTemplate(context, "VehicleCard.mustache");
      VehicleListControl vehicleCtrl = new VehicleListControl();
      List<VehicleEntity> vehicles = vehicleCtrl.getList(request.getParameterMap());
      String vehiclesHtml = "";
      if (vehicles.size() > 0) {
        for (VehicleEntity vehicle : vehicles) {
          if (vehicle.getLocation().compareTo(loc.getUid()) == 0) {
            cardTemplate.setVariables(vehicle.getCustomerData());
            vehiclesHtml = vehiclesHtml + cardTemplate.render();
          } 
        } 
      } else {
        vehiclesHtml = vehiclesHtml + "<h4>There aren't vehicles here</h4>";
      } 
      view.setVariable("vehicles", vehiclesHtml);
      lr.setContent(view.render());
      lr.render(response);
    } catch (InvalidUrlException e) {
      lr.setContent((new Alert(context, "Invalid URL. No UID specified")).render());
      lr.render(response);
      return;
    } catch (RecordNotFoundException e) {
      lr.setContent((new Alert(context, "Invalid UID. Location not found")).render());
      lr.render(response);
      return;
    } 
  }
}
