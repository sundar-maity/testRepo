package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.VehicleTypeCreateControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleTypeCreateUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    VehicleTypeCreateControl control = new VehicleTypeCreateControl();
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate createForm = new SimpleTemplate(context, "VehicleTypeCreateForm.mustache");
    lr.setTitle("Define new vehicle type");
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    if (type == RequestType.POST) {
      try {
        control.create(request);
        CarRentalServlet.redirect(context, response, "/vehicletypes");
      } catch (InvalidInputException e) {
        createForm.setVariable("alerts", e.getMessagesHtml(context));
        lr.setContent(createForm.render());
        lr.render(response);
      } catch (Exception e) {
        e.printStackTrace();
        createForm.setVariable("alerts", (new Alert(context, "Something went wrong")).render());
        lr.setContent(createForm.render());
        lr.render(response);
      } 
    } else {
      lr.setContent(createForm.render());
      lr.render(response);
    } 
  }
}
