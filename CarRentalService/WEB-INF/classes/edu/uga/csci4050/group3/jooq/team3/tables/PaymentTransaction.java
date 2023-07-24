package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.PaymentTransactionRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class PaymentTransaction extends TableImpl<PaymentTransactionRecord> {
  private static final long serialVersionUID = 1969733338L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction PAYMENT_TRANSACTION = new edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction();
  
  public Class<PaymentTransactionRecord> getRecordType() {
    return PaymentTransactionRecord.class;
  }
  
  public final TableField<PaymentTransactionRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, Integer> DATE = createField("date", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> METHOD = createField("method", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> DESCRIPTION = createField("description", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> USER = createField("user", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> REASON = createField("reason", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> COMMENTS = createField("comments", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<PaymentTransactionRecord, String> STATUS = createField("status", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public PaymentTransaction() {
    super("PAYMENT_TRANSACTION", (Schema)Team3.TEAM3);
  }
  
  public PaymentTransaction(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)PAYMENT_TRANSACTION);
  }
  
  public Identity<PaymentTransactionRecord, Integer> getIdentity() {
    return Keys.IDENTITY_PAYMENT_TRANSACTION;
  }
  
  public UniqueKey<PaymentTransactionRecord> getPrimaryKey() {
    return Keys.KEY_PAYMENT_TRANSACTION_PRIMARY;
  }
  
  public List<UniqueKey<PaymentTransactionRecord>> getKeys() {
    return Arrays.asList((UniqueKey<PaymentTransactionRecord>[])new UniqueKey[] { Keys.KEY_PAYMENT_TRANSACTION_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction(alias);
  }
}
