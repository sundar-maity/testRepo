package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.UserRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class User extends TableImpl<UserRecord> {
  private static final long serialVersionUID = -612563568L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.User USER = new edu.uga.csci4050.group3.jooq.team3.tables.User();
  
  public Class<UserRecord> getRecordType() {
    return UserRecord.class;
  }
  
  public final TableField<UserRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<UserRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> USERNAME = createField("username", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> PASSWORD = createField("password", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> EMAIL = createField("email", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> FIRST_NAME = createField("first_name", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> LAST_NAME = createField("last_name", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> ROLE = createField("role", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> LICENSE = createField("license", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, Integer> DATEOFBIRTH = createField("dateofbirth", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<UserRecord, String> STREET_ADDRESS = createField("street_address", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> STATE = createField("state", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, String> COUNTRY = createField("country", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, Integer> ZIPCODE = createField("zipcode", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<UserRecord, String> CITY = createField("city", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<UserRecord, Integer> MEMBERSHIP_EXPIRATION = createField("membership_expiration", SQLDataType.INTEGER, (Table)this);
  
  public User() {
    super("USER", (Schema)Team3.TEAM3);
  }
  
  public User(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)USER);
  }
  
  public Identity<UserRecord, Integer> getIdentity() {
    return Keys.IDENTITY_USER;
  }
  
  public UniqueKey<UserRecord> getPrimaryKey() {
    return Keys.KEY_USER_PRIMARY;
  }
  
  public List<UniqueKey<UserRecord>> getKeys() {
    return Arrays.asList((UniqueKey<UserRecord>[])new UniqueKey[] { Keys.KEY_USER_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.User as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.User(alias);
  }
}
