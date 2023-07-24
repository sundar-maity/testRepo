package WEB-INF.classes.edu.uga.csci4050.group3.admin;

import edu.uga.csci4050.group3.core.PaymentTransactionEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class PaymentListControl {
  public List<PaymentTransactionEntity> getPayments() {
    try {
      return DatabaseAbstraction.getPaymentTransactions();
    } catch (RecordNotFoundException e) {
      return new ArrayList<>();
    } 
  }
}
