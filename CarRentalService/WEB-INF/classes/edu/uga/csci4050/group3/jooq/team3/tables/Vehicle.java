package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables;

import edu.uga.csci4050.group3.jooq.team3.Keys;
import edu.uga.csci4050.group3.jooq.team3.Team3;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleRecord;
import java.util.Arrays;
import java.util.List;
import org.jooq.Identity;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

public class Vehicle extends TableImpl<VehicleRecord> {
  private static final long serialVersionUID = 2141882332L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.tables.Vehicle VEHICLE = new edu.uga.csci4050.group3.jooq.team3.tables.Vehicle();
  
  public Class<VehicleRecord> getRecordType() {
    return VehicleRecord.class;
  }
  
  public final TableField<VehicleRecord, Integer> ID = createField("id", SQLDataType.INTEGER.nullable(false), (Table)this);
  
  public final TableField<VehicleRecord, String> UID = createField("uid", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleRecord, String> TYPE = createField("type", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleRecord, String> MAKE = createField("make", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleRecord, String> MODEL = createField("model", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleRecord, Integer> YEAR = createField("year", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<VehicleRecord, Integer> MILEAGE = createField("mileage", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<VehicleRecord, String> TAG = createField("tag", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public final TableField<VehicleRecord, Integer> LASTSERVICE = createField("lastservice", SQLDataType.INTEGER, (Table)this);
  
  public final TableField<VehicleRecord, String> LOCATION = createField("location", SQLDataType.VARCHAR.length(255), (Table)this);
  
  public Vehicle() {
    super("VEHICLE", (Schema)Team3.TEAM3);
  }
  
  public Vehicle(String alias) {
    super(alias, (Schema)Team3.TEAM3, (Table)VEHICLE);
  }
  
  public Identity<VehicleRecord, Integer> getIdentity() {
    return Keys.IDENTITY_VEHICLE;
  }
  
  public UniqueKey<VehicleRecord> getPrimaryKey() {
    return Keys.KEY_VEHICLE_PRIMARY;
  }
  
  public List<UniqueKey<VehicleRecord>> getKeys() {
    return Arrays.asList((UniqueKey<VehicleRecord>[])new UniqueKey[] { Keys.KEY_VEHICLE_PRIMARY });
  }
  
  public edu.uga.csci4050.group3.jooq.team3.tables.Vehicle as(String alias) {
    return new edu.uga.csci4050.group3.jooq.team3.tables.Vehicle(alias);
  }
}
