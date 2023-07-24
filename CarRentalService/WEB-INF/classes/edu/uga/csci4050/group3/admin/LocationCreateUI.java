package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.LocationCreateControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LocationCreateUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate createForm = new SimpleTemplate(context, "LocationCreateForm.mustache");
    SimpleTemplate countryList = new SimpleTemplate(context, "CountrySelectInput.mustache");
    SimpleTemplate stateList = new SimpleTemplate(context, "StateSelectInput.mustache");
    LocationCreateControl control = new LocationCreateControl();
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    countryList.setVariable("name", "locationCountry");
    createForm.setVariable("select_country", countryList.render());
    stateList.setVariable("name", "locationState");
    createForm.setVariable("select_state", stateList.render());
    if (type == RequestType.POST) {
      try {
        control.create(request);
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/locations"));
      } catch (InvalidInputException e) {
        createForm.setVariable("alerts", e.getMessagesHtml(context));
        lr.setContent(createForm.render());
        lr.render(response);
      } catch (IOException e) {
        e.printStackTrace();
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
