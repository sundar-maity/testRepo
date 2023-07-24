package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalTransactionRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class RentalTransaction extends TableImpl<RentalTransactionRecord> {
  private static final long serialVersionUID = 1823640524L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction RENTAL_TRANSACTION = new edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction();
  
  public Class<RentalTransactionRecord> getRecordType() {
    return RentalTransactionRecord.class;
  }
  
  public final TableField<RentalTransactionRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<RentalTransactionRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalTransactionRecord, Integer> START_DATE = createField("start_date", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<RentalTransactionRecord, Integer> END_DATE = createField("end_date", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<RentalTransactionRecord, String> USER = createField("user", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalTransactionRecord, String> VEHICLE = createField("vehicle", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalTransactionRecord, String> STATUS = createField("status", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalTransactionRecord, String> COMMENTS = createField("comments", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public RentalTransaction() {
    super("RENTAL_TRANSACTION", (Schema)Team3.TEAM3);
  }
  
  public RentalTransaction(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)RENTAL_TRANSACTION);
  }
  
  public Identity<RentalTransactionRecord, Integer> getIdentity() {
    return Keys.IDENTITY_RENTAL_TRANSACTION;
  }
  
  public UniqueKey<RentalTransactionRecord> getPrimaryKey() {
    return Keys.KEY_RENTAL_TRANSACTION_PRIMARY;
  }
  
  public List<UniqueKey<RentalTransactionRecord>> getKeys() {
    return Arrays.asList((UniqueKey<RentalTransactionRecord>[])new UniqueKey[] { Keys.KEY_RENTAL_TRANSACTION_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction(alias);
  }
}
