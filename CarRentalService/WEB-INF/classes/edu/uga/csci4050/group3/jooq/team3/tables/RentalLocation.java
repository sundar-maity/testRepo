package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalLocationRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class RentalLocation extends TableImpl<RentalLocationRecord> {
  private static final long serialVersionUID = 1018944360L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation RENTAL_LOCATION = new edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation();
  
  public Class<RentalLocationRecord> getRecordType() {
    return RentalLocationRecord.class;
  }
  
  public final TableField<RentalLocationRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<RentalLocationRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, String> NAME = createField("name", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, String> STREET_ADDRESS = createField("street_address", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, String> STATE = createField("state", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, String> COUNTRY = createField("country", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, Integer> ZIPCODE = createField("zipcode", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<RentalLocationRecord, String> CITY = createField("city", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<RentalLocationRecord, Integer> CAPACITY = createField("capacity", SQLDataType.INTEGER, (Table)this);
  
  public RentalLocation() {
    super("RENTAL_LOCATION", (Schema)Team3.TEAM3);
  }
  
  public RentalLocation(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)RENTAL_LOCATION);
  }
  
  public Identity<RentalLocationRecord, Integer> getIdentity() {
    return Keys.IDENTITY_RENTAL_LOCATION;
  }
  
  public UniqueKey<RentalLocationRecord> getPrimaryKey() {
    return Keys.KEY_RENTAL_LOCATION_PRIMARY;
  }
  
  public List<UniqueKey<RentalLocationRecord>> getKeys() {
    return Arrays.asList((UniqueKey<RentalLocationRecord>[])new UniqueKey[] { Keys.KEY_RENTAL_LOCATION_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation(alias);
  }
}
