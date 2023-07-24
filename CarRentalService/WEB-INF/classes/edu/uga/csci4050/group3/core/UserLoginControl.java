package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserLoginControl {
  public boolean login(HttpServletRequest request, HttpServletResponse response) throws InvalidInputException, AuthenticationException {
    Map<String, String[]> params = request.getParameterMap();
    if (!params.containsKey("userUsername"))
      throw new InvalidInputException(); 
    if (!params.containsKey("userPassword"))
      throw new InvalidInputException(); 
    String username = ((String[])params.get("userUsername"))[0];
    String password = ((String[])params.get("userPassword"))[0];
    try {
      UserEntity user = DatabaseAbstraction.getUserByUsername(username);
      if (!user.getPassword().equals(password))
        throw new AuthenticationException(); 
      SessionManagement sessMan = new SessionManagement(request, response);
      sessMan.createSession(username);
    } catch (RecordNotFoundException e) {
      throw new AuthenticationException();
    } catch (SessionException e) {
      throw new AuthenticationException();
    } 
    return true;
  }
}
