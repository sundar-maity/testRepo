package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class RentalListControl {
  public List<RentalTransactionEntity> getList() {
    try {
      List<RentalTransactionEntity> list = DatabaseAbstraction.getRentalTransactions();
      return list;
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
}
