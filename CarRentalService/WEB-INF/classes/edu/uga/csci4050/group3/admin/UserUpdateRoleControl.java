package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import javax.servlet.http.HttpServletRequest;

public class UserUpdateRoleControl {
  public void updateRole(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    UserEntity user = DatabaseAbstraction.getUser(request.getParameter("uid"));
    if (user.getRoleEnum() == UserType.ADMIN) {
      user.setRoleFromEnum(UserType.CUSTOMER);
    } else {
      user.setRoleFromEnum(UserType.ADMIN);
    } 
    DatabaseAbstraction.updateUser(user);
  }
}
