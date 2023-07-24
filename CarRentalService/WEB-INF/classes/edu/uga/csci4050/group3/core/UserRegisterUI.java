package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserRegisterControl;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserRegisterUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate registerForm = new SimpleTemplate(context, "UserRegisterForm.mustache");
    lr.setTitle("Register");
    SessionManagement sessMan = new SessionManagement(request, response);
    if (sessMan.isUserLoggedIn()) {
      try {
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/user/home"));
      } catch (IOException e) {
        e.printStackTrace();
      } 
      return;
    } 
    if (type == RequestType.GET) {
      lr.setContent(registerForm.render());
      lr.render(response);
    } else {
      UserRegisterControl urc = new UserRegisterControl();
      try {
        urc.register(request);
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/user/login"));
      } catch (InvalidInputException e) {
        registerForm.setVariable("alerts", e.getMessagesHtml(context));
        lr.setContent(registerForm.render());
        lr.render(response);
      } catch (Exception e) {
        e.printStackTrace();
        Alert error = new Alert(context);
        error.setContent("Something went wrong!");
        registerForm.setVariable("alerts", error.render());
        lr.setContent(registerForm.render());
        lr.render(response);
      } 
    } 
  }
}
