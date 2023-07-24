package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.VehicleTypeListControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleTypeListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate list = new SimpleTemplate(context, "VehicleTypeList.mustache");
    lr.setTitle("Vehicle types");
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    VehicleTypeListControl control = new VehicleTypeListControl();
    String typesHtml = "";
    if (control.count() > 0) {
      List<VehicleTypeEntity> types = control.list();
      for (VehicleTypeEntity vtype : types) {
        SimpleTemplate row = new SimpleTemplate(context, "VehicleTypeRow.mustache");
        row.setVariables(vtype.getData());
        typesHtml = typesHtml + row.render();
      } 
    } else {
      list.setVariable("message", "<h4>No vehicle types defined</h4>");
    } 
    list.setVariable("types", typesHtml);
    lr.setContent(list.render());
    lr.render(response);
  }
}
