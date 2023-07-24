package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RentalStatus;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.customer.ReservationListControl;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate list = new SimpleTemplate(context, "ReservationList.mustache");
    ReservationListControl control = new ReservationListControl();
    Calendar cal = Calendar.getInstance();
    lr.setTitle("Reservations");
    ArrayList<UserType> authTypes = new ArrayList<>();
    authTypes.add(UserType.ADMIN);
    authTypes.add(UserType.CUSTOMER);
    if ((new SessionManagement(request, response)).requireRole(authTypes, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    try {
      list.setVariable("username", (new SessionManagement(request, response)).getLoggedinUsername());
    } catch (AuthenticationException authenticationException) {}
    try {
      List<RentalTransactionEntity> tlist = control.getList(request, response);
      if (tlist.size() > 0) {
        String locationsHtml = "";
        for (RentalTransactionEntity rental : tlist) {
          cal.setTime(rental.getStart_dateDate());
          cal.add(10, -1);
          if (DatabaseAbstraction.getTimestampFromDate(cal.getTime()) > DatabaseAbstraction.getTimestampFromDate(new Date())) {
            if (rental.getStatusEnum() == RentalStatus.CANCELLED) {
              SimpleTemplate simpleTemplate1 = new SimpleTemplate(context, "ReservationRowStatic.mustache");
              simpleTemplate1.setVariables(rental.getData(true));
              locationsHtml = locationsHtml + simpleTemplate1.render();
              continue;
            } 
            SimpleTemplate simpleTemplate = new SimpleTemplate(context, "ReservationRowCancel.mustache");
            simpleTemplate.setVariables(rental.getData(true));
            locationsHtml = locationsHtml + simpleTemplate.render();
            continue;
          } 
          if (rental.getEnd_date() < DatabaseAbstraction.getTimestampFromDate(new Date())) {
            if (rental.getStatusEnum() == RentalStatus.ACTIVE) {
              SimpleTemplate simpleTemplate1 = new SimpleTemplate(context, "ReservationRowReturn.mustache");
              simpleTemplate1.setVariables(rental.getData(true));
              locationsHtml = locationsHtml + simpleTemplate1.render();
              continue;
            } 
            SimpleTemplate simpleTemplate = new SimpleTemplate(context, "ReservationRowStatic.mustache");
            simpleTemplate.setVariables(rental.getData(true));
            locationsHtml = locationsHtml + simpleTemplate.render();
            continue;
          } 
          if (rental.getStatusEnum() == RentalStatus.RETURNED) {
            SimpleTemplate simpleTemplate = new SimpleTemplate(context, "ReservationRowStatic.mustache");
            simpleTemplate.setVariables(rental.getData(true));
            locationsHtml = locationsHtml + simpleTemplate.render();
            continue;
          } 
          SimpleTemplate rentalRow = new SimpleTemplate(context, "ReservationRowReturn.mustache");
          rentalRow.setVariables(rental.getData(true));
          locationsHtml = locationsHtml + rentalRow.render();
        } 
        list.setVariable("rentals", locationsHtml);
      } else {
        list.setVariable("message", (new Alert(context, "We don't have ny rentals in our records")).render());
      } 
    } catch (AuthenticationException e) {
      lr.setContent((new Alert(context, "Invalid user session")).render());
      lr.render(response);
    } catch (RecordNotFoundException e) {
      lr.setContent((new Alert(context, "Invalid user session")).render());
      lr.render(response);
    } 
    lr.setContent(list.render());
    lr.render(response);
  }
}
