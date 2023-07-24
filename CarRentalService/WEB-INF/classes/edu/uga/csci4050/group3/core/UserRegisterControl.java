package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class UserRegisterControl {
  public void register(HttpServletRequest request) throws InvalidInputException {
    UserEntity user = new UserEntity();
    InvalidInputException iie = new InvalidInputException();
    user.loadFromForm(request.getParameterMap());
    user.setRoleFromEnum(UserType.CUSTOMER);
    try {
      UserEntity check = DatabaseAbstraction.getUserByUsername(user.getUsername());
      iie.addMessage("Username is taken");
    } catch (RecordNotFoundException recordNotFoundException) {}
    int diff = DatabaseAbstraction.getTimestampFromDate(new Date()) - user.getDateofbirth();
    if (diff < 16675200)
      iie.addMessage("In order to register you need to be at least 21 years old"); 
    if (iie.countMessages() > 0)
      throw iie; 
    user.validate();
    DatabaseAbstraction.putUser(user);
  }
}
