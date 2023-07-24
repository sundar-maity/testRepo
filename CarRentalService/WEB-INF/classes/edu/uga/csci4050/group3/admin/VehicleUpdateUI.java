package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.VehicleUpdateControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleUpdateUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    VehicleUpdateControl control = new VehicleUpdateControl();
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate updateForm = new SimpleTemplate(context, "VehicleUpdateForm.mustache");
    lr.setTitle("Vehicle update");
    lr.setTitle("Update vehicle");
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      VehicleEntity vehicle = control.getVehicle(request);
      updateForm.setVariables(vehicle.getAdminData());
    } catch (RecordNotFoundException ex) {
      lr.setContent((new Alert(context, "Vehicle with UID not found")).render());
      lr.render(response);
      return;
    } catch (InvalidUrlException ex) {
      lr.setContent((new Alert(context, "UID wasn't specified")).render());
      lr.render(response);
      return;
    } 
    updateForm.setVariable("select_types", DatabaseAbstraction.getVehicleTypesSelect());
    updateForm.setVariable("select_locations", DatabaseAbstraction.getLocationsSelect());
    if (type == RequestType.POST) {
      try {
        control.updateVehicle(request);
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/vehicles"));
      } catch (InvalidInputException e) {
        updateForm.setVariable("alerts", e.getMessagesHtml(context));
        lr.setContent(updateForm.render());
        lr.render(response);
      } catch (IOException e) {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
        updateForm.setVariable("alerts", (new Alert(context, "Something went wrong")).render());
        lr.setContent(updateForm.render());
        lr.render(response);
      } 
    } else {
      lr.setContent(updateForm.render());
      lr.render(response);
    } 
  }
}
