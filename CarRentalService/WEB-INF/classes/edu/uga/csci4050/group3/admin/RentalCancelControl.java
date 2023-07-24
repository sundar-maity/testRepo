package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RentalStatus;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import javax.servlet.http.HttpServletRequest;

public class RentalCancelControl {
  public void cancel(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid"))
      throw new InvalidUrlException(); 
    RentalTransactionEntity rental = DatabaseAbstraction.getRentalTransaction(request.getParameter("uid"));
    rental.setStatusEnum(RentalStatus.CANCELLED);
    DatabaseAbstraction.updateRentalTransaction(rental);
  }
}
