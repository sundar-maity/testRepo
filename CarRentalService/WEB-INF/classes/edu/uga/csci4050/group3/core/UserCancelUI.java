package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserCancelControl;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.ConfirmationDialog;
import edu.uga.csci4050.group3.template.LayoutRoot;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCancelUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    UserCancelControl control = new UserCancelControl();
    lr.setTitle("Cancel Account");
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      if (control.processCancellation(request, response)) {
        CarRentalServlet.redirect(context, response, "/user/logout");
        return;
      } 
    } catch (AuthenticationException e) {
      lr.setContent((new Alert(context, "Unable to process your request. Invalid user session")).render());
      lr.render(response);
      return;
    } catch (RecordNotFoundException e) {
      lr.setContent((new Alert(context, "Unable to process your request. Unable to find user records")).render());
      lr.render(response);
      return;
    } 
    ConfirmationDialog dialog = new ConfirmationDialog(context, "Delete your account from the system", CarRentalServlet.getFullURL(context, "/user/delete?confirm=yes"), CarRentalServlet.getFullURL(context, "/user/home"));
    lr.setContent(dialog.render());
    lr.render(response);
  }
}
