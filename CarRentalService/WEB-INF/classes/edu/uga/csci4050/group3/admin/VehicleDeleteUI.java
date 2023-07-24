package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.VehicleDeleteControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.ConfirmationDialog;
import edu.uga.csci4050.group3.template.LayoutRoot;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleDeleteUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    if (!request.getParameterMap().containsKey("uid")) {
      (new Alert(context, "UID wasn't specified.")).render(response);
      return;
    } 
    if (request.getParameterMap().containsKey("confirm") && ((String[])request.getParameterMap().get("confirm"))[0].equals("yes")) {
      VehicleDeleteControl control = new VehicleDeleteControl();
      try {
        control.deleteVehicle(request.getParameter("uid"));
        CarRentalServlet.redirect(context, response, "/vehicles");
        return;
      } catch (RecordNotFoundException e) {
        (new Alert(context, "Unable to find vehicle with UID: " + request.getParameter("uid"))).render(response);
        return;
      } 
    } 
    LayoutRoot lr = new LayoutRoot(context, request, response);
    ConfirmationDialog dialog = new ConfirmationDialog(context, "Delete vehicle with UID: " + request.getParameter("uid"), CarRentalServlet.getFullURL(context, "/vehicle/delete?confirm=yes&uid=" + request.getParameter("uid")), CarRentalServlet.getFullURL(context, "/vehicles"));
    lr.setContent(dialog.render());
    lr.render(response);
  }
}
