package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.customer.MembershipControl;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MembershipUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate membershipLayout = new SimpleTemplate(context, "Membership.mustache");
    lr.setTitle("Membership management");
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    MembershipControl control = new MembershipControl();
    try {
      if (!control.customerHasMembership(request, response)) {
        membershipLayout.setVariable("message", "<h4>According to our records you don't have a membership with us yet.</h4>");
        membershipLayout.setVariable("prompt", "<p>Would you like to get one?</p>");
        membershipLayout.setVariable("expiration", "Not available");
        membershipLayout.setVariable("type", "Purchase");
      } else {
        membershipLayout.setVariable("message", "<h4>You already have a membership.</h4>");
        membershipLayout.setVariable("prompt", "<p>Would you like to extend it?</p>");
        membershipLayout.setVariable("expiration", control.getExpirationDateString(request, response));
        membershipLayout.setVariable("type", "Extend");
      } 
      lr.setContent(membershipLayout.render());
      lr.render(response);
    } catch (AuthenticationException e) {
      lr.setContent((new Alert(context, "Unable to authenticate user")).render());
      lr.render(response);
    } catch (RecordNotFoundException e) {
      lr.setContent((new Alert(context, "Unable to authenticate user")).render());
      lr.render(response);
    } 
  }
}
