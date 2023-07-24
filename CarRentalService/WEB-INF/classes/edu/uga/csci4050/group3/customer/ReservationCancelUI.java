package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.customer.ReservationCancelControl;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationCancelUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    ReservationCancelControl control = new ReservationCancelControl();
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      control.cancel(request, response);
      CarRentalServlet.redirect(context, response, "/reservations");
    } catch (InvalidUrlException e) {
      LayoutRoot lr = new LayoutRoot(context, request, response);
      lr.setContent((new Alert(context, "Invalid UID provided")).render());
      lr.render(response);
    } catch (RecordNotFoundException e) {
      LayoutRoot lr = new LayoutRoot(context, request, response);
      lr.setContent((new Alert(context, "Invalid UID provided")).render());
      lr.render(response);
    } catch (AuthenticationException e) {
      LayoutRoot lr = new LayoutRoot(context, request, response);
      lr.setContent((new Alert(context, "Invalid UID provided. User does not own rental transaction.")).render());
      lr.render(response);
    } 
  }
}
