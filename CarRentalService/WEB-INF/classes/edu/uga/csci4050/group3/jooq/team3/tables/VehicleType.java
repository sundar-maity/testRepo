package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleTypeRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class VehicleType extends TableImpl<VehicleTypeRecord> {
  private static final long serialVersionUID = 1992205928L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.VehicleType VEHICLE_TYPE = new edu.uga.csci4050.group3.jooq.team3.tables.VehicleType();
  
  public Class<VehicleTypeRecord> getRecordType() {
    return VehicleTypeRecord.class;
  }
  
  public final TableField<VehicleTypeRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<VehicleTypeRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleTypeRecord, String> NAME = createField("name", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleTypeRecord, Double> HOURLY_RATE = createField("hourly_rate", SQLDataType.FLOAT, (Table)this);
  
  public final TableField<VehicleTypeRecord, Double> DAILY_RATE = createField("daily_rate", SQLDataType.FLOAT, (Table)this);
  
  public VehicleType() {
    super("VEHICLE_TYPE", (Schema)Team3.TEAM3);
  }
  
  public VehicleType(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)VEHICLE_TYPE);
  }
  
  public Identity<VehicleTypeRecord, Integer> getIdentity() {
    return Keys.IDENTITY_VEHICLE_TYPE;
  }
  
  public UniqueKey<VehicleTypeRecord> getPrimaryKey() {
    return Keys.KEY_VEHICLE_TYPE_PRIMARY;
  }
  
  public List<UniqueKey<VehicleTypeRecord>> getKeys() {
    return Arrays.asList((UniqueKey<VehicleTypeRecord>[])new UniqueKey[] { Keys.KEY_VEHICLE_TYPE_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.VehicleType as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.VehicleType(alias);
  }
}
