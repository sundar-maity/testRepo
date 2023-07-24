package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.admin.UserListControl;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserListUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate list = new SimpleTemplate(context, "UserList.mustache");
    if ((new SessionManagement(request, response)).requireRole(UserType.ADMIN, CarRentalServlet.getFullURL(context, "/user/home")))
      return; 
    UserListControl control = new UserListControl();
    String usersHtml = "";
    if (control.count() > 0) {
      List<UserEntity> types = control.list();
      for (UserEntity user : types) {
        SimpleTemplate row = new SimpleTemplate(context, "UserRow.mustache");
        row.setVariables(user.getData());
        usersHtml = usersHtml + row.render();
      } 
    } else {
      list.setVariable("message", "<h4>No users here. How did you mke it here?</h4>");
    } 
    list.setVariable("users", usersHtml);
    lr.setContent(list.render());
    lr.render(response);
  }
}
