package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAccountControl {
  public void update(HttpServletRequest request, HttpServletResponse response) throws InvalidInputException, RecordNotFoundException, AuthenticationException {
    SessionManagement sessMan = new SessionManagement(request, response);
    InvalidInputException iie = new InvalidInputException();
    UserEntity currentInfo = null, newInfo = null;
    newInfo = new UserEntity();
    try {
      currentInfo = DatabaseAbstraction.getUserByUsername(sessMan.getLoggedinUsername());
    } catch (AuthenticationException e1) {
      e1.printStackTrace();
    } 
    newInfo.loadFromForm(request.getParameterMap());
    if (!newInfo.getUsername().equals(currentInfo.getUsername()))
      try {
        UserEntity check = DatabaseAbstraction.getUserByUsername(newInfo.getUsername());
        iie.addMessage("Username is taken");
      } catch (RecordNotFoundException recordNotFoundException) {} 
    int diff = DatabaseAbstraction.getTimestampFromDate(new Date()) - newInfo.getDateofbirth();
    if (diff < 16675200)
      iie.addMessage("In order to register you need to be at least 21 years old"); 
    if (iie.countMessages() > 0)
      throw iie; 
    currentInfo.loadFromForm(request.getParameterMap());
    DatabaseAbstraction.updateUser(currentInfo);
    sessMan.updateLoggedinUsername(currentInfo.getUsername());
  }
}
