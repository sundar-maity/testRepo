package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.UserUpdateRoleControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserUpdateRoleUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    UserUpdateRoleControl control = new UserUpdateRoleControl();
    try {
      control.updateRole(request);
      CarRentalServlet.redirect(context, response, "/users");
    } catch (InvalidUrlException e) {
      LayoutRoot lr = new LayoutRoot(context, request, response);
      lr.setContent((new Alert(context, "Invalid UID")).render());
      lr.render(response);
    } catch (RecordNotFoundException e) {
      LayoutRoot lr = new LayoutRoot(context, request, response);
      lr.setContent((new Alert(context, "Could not find a user with UID")).render());
      lr.render(response);
    } 
  }
}
