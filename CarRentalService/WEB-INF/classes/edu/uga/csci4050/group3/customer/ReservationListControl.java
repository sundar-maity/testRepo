package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReservationListControl {
  public List<RentalTransactionEntity> getList(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, RecordNotFoundException {
    String username = (new SessionManagement(request, response)).getLoggedinUsername();
    String uid = DatabaseAbstraction.getUserByUsername(username).getUid();
    try {
      List<RentalTransactionEntity> list = DatabaseAbstraction.getRentalTransactionsForUser(uid);
      return list;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
}
