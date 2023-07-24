package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserLogoutControl;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLogoutUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    UserLogoutControl ulc = new UserLogoutControl();
    if (!ulc.isLoggedIn(request, response))
      response.addHeader("Location", CarRentalServlet.getFullURL(context, "/home")); 
    ulc.logout(request, response);
    response.addHeader("Location", CarRentalServlet.getFullURL(context, "/home"));
    try {
      response.sendRedirect(CarRentalServlet.getFullURL(context, "/home"));
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
}
