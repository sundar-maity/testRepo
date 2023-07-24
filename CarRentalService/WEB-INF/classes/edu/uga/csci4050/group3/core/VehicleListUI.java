package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleListControl;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    VehicleListControl control = new VehicleListControl();
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate listTemplate = new SimpleTemplate(context, "VehicleList.mustache");
    lr.setTitle("List vehicles");
    List<VehicleEntity> vehicles = control.getList(request.getParameterMap());
    SimpleTemplate cardTemplate = new SimpleTemplate(context, "VehicleCard.mustache");
    SimpleTemplate adminMenu = new SimpleTemplate(context, "VehicleListAdminMenu.mustache");
    String vehiclesHtml = "";
    boolean isAdmin = control.isAdmin(request, response);
    if (isAdmin) {
      listTemplate.setVariable("extra_options", adminMenu.render());
      cardTemplate = new SimpleTemplate(context, "VehicleCardAdmin.mustache");
    } 
    if (vehicles.size() > 0) {
      vehiclesHtml = vehiclesHtml + "<div class=\"row pad-top-10\">";
      for (VehicleEntity vehicle : vehicles) {
        cardTemplate.setVariables(vehicle.getCustomerData());
        vehiclesHtml = vehiclesHtml + cardTemplate.render();
      } 
      vehiclesHtml = vehiclesHtml + "</div>";
    } else {
      vehiclesHtml = vehiclesHtml + "<h4>There aren't vehicles here</h4>";
    } 
    listTemplate.setVariable("list", vehiclesHtml);
    lr.setContent(listTemplate.render());
    lr.render(response);
  }
}
