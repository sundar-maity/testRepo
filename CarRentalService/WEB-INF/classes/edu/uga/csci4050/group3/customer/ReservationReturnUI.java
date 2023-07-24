package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.customer.ReservationReturnControl;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationReturnUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate returnLayout = new SimpleTemplate(context, "ReservationReturn.mustache");
    ReservationReturnControl control = new ReservationReturnControl();
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      if (!control.isReturningPossible(request)) {
        CarRentalServlet.redirect(context, response, "/reservations");
        return;
      } 
    } catch (InvalidUrlException e1) {
      CarRentalServlet.redirect(context, response, "/reservations");
      return;
    } catch (RecordNotFoundException e1) {
      CarRentalServlet.redirect(context, response, "/reservations");
      return;
    } 
    lr.setTitle("Return vehicle");
    if (type == RequestType.GET) {
      try {
        double additionalChanges = control.getAdditionalCharges(request);
        if (additionalChanges > 0.0D) {
          SimpleTemplate chargesLayout = new SimpleTemplate(context, "ReservationAdditionalCosts.mustache");
          SimpleTemplate payLayout = new SimpleTemplate(context, "ReservationPaymentForm.mustache");
          chargesLayout.setVariable("amount", String.valueOf(additionalChanges));
          payLayout.setVariable("uid", request.getParameter("uid"));
          returnLayout.setVariable("additional_costs", chargesLayout.render());
          returnLayout.setVariable("additional_forms", payLayout.render());
          lr.setContent(returnLayout.render());
          lr.render(response);
        } else {
          SimpleTemplate returnForm = new SimpleTemplate(context, "ReservationReturnForm.mustache");
          returnForm.setVariable("uid", request.getParameter("uid"));
          returnLayout.setVariable("additional_costs", "<p>No additional costs</p>");
          returnLayout.setVariable("additional_forms", returnForm.render());
          lr.setContent(returnLayout.render());
          lr.render(response);
          return;
        } 
      } catch (InvalidUrlException e) {
        lr.setContent((new Alert(context, "Unable to process your request.")).render());
        lr.render(response);
        return;
      } catch (RecordNotFoundException e) {
        lr.setContent((new Alert(context, "Unable to process your request. Record not found")).render());
        lr.render(response);
        return;
      } 
    } else {
      try {
        control.processReturn(request);
        CarRentalServlet.redirect(context, response, "/reservations");
        return;
      } catch (InvalidUrlException e) {
        lr.setContent((new Alert(context, "Unable to process your request.")).render());
        lr.render(response);
        return;
      } catch (InvalidInputException e) {
        lr.setContent(e.getMessagesHtml(context));
        lr.render(response);
        return;
      } catch (RecordNotFoundException e) {
        lr.setContent((new Alert(context, "Unable to process your request. Record not found")).render());
        lr.render(response);
        return;
      } 
    } 
  }
}
