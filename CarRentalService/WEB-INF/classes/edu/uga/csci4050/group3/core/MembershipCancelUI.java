package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.MembershipCancelControl;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.ConfirmationDialog;
import edu.uga.csci4050.group3.template.LayoutRoot;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembershipCancelUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    MembershipCancelControl control = new MembershipCancelControl();
    lr.setTitle("Terminate Membership");
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      if (control.processCancellation(request, response)) {
        CarRentalServlet.redirect(context, response, "/membership");
        return;
      } 
    } catch (InvalidInputException e) {
      lr.setContent(e.getMessagesHtml(context));
      lr.render(response);
    } 
    ConfirmationDialog dialog = new ConfirmationDialog(context, "Terminate your membership.", CarRentalServlet.getFullURL(context, "/membership/cancel?confirm=yes"), CarRentalServlet.getFullURL(context, "/user/home"));
    lr.setContent(dialog.render());
    lr.render(response);
  }
}
