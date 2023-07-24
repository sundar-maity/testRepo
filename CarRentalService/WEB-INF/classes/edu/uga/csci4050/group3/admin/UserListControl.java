package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserListControl {
  public List<UserEntity> list() {
    try {
      List<UserEntity> types = DatabaseAbstraction.getUsers();
      return types;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
  
  public int count() {
    try {
      List<UserEntity> types = DatabaseAbstraction.getUsers();
      return types.size();
    } catch (RecordNotFoundException e) {
      return 0;
    } 
  }
}
