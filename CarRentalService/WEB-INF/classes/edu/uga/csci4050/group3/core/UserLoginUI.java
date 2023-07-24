package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserLoginControl;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate loginForm = new SimpleTemplate(context, "LoginForm.mustache");
    SimpleTemplate centCol = new SimpleTemplate(context, "CenteredColumn.mustache");
    lr.setTitle("Login");
    if (type == RequestType.GET) {
      centCol.setVariable("content", loginForm.render());
      lr.setContent(centCol.render());
      lr.render(response);
    } else {
      UserLoginControl ulc = new UserLoginControl();
      try {
        ulc.login(request, response);
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/user/home"));
      } catch (InvalidInputException e) {
        Alert alert = new Alert(context);
        alert.setContent("Invalid input. Check username and password");
        loginForm.setVariable("alert", alert.render());
        centCol.setVariable("content", loginForm.render());
        lr.setContent(centCol.render());
        lr.render(response);
      } catch (AuthenticationException e) {
        Alert alert = new Alert(context);
        alert.setContent("Invalid username and password combination");
        loginForm.setVariable("alert", alert.render());
        centCol.setVariable("content", loginForm.render());
        lr.setContent(centCol.render());
        lr.render(response);
      } catch (IOException e) {
        e.printStackTrace();
      } 
    } 
  }
}
