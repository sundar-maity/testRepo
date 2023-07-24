package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.RentalListControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RentalListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate list = new SimpleTemplate(context, "RentalList.mustache");
    RentalListControl control = new RentalListControl();
    lr.setTitle("Rentals");
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    List<RentalTransactionEntity> tlist = control.getList();
    if (tlist.size() > 0) {
      String locationsHtml = "";
      for (RentalTransactionEntity rental : tlist) {
        SimpleTemplate rentalRow = new SimpleTemplate(context, "RentalRow.mustache");
        rentalRow.setVariables(rental.getData(true));
        locationsHtml = locationsHtml + rentalRow.render();
      } 
      list.setVariable("rentals", locationsHtml);
    } else {
      list.setVariable("message", "<h4>No rentals found</h4>");
    } 
    lr.setContent(list.render());
    lr.render(response);
  }
}
