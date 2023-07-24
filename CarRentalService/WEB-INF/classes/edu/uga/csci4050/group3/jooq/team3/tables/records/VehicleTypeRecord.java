package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.VehicleType;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleTypeRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record5;
import org.jooq.Row;
import org.jooq.Row5;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class VehicleTypeRecord extends UpdatableRecordImpl<VehicleTypeRecord> implements Record5<Integer, String, String, Double, Double> {
  private static final long serialVersionUID = 1612765118L;
  
  public void setId(Integer value) {
    setValue(0, value);
  }
  
  public Integer getId() {
    return (Integer)getValue(0);
  }
  
  public void setUid(String value) {
    setValue(1, value);
  }
  
  public String getUid() {
    return (String)getValue(1);
  }
  
  public void setName(String value) {
    setValue(2, value);
  }
  
  public String getName() {
    return (String)getValue(2);
  }
  
  public void setHourlyRate(Double value) {
    setValue(3, value);
  }
  
  public Double getHourlyRate() {
    return (Double)getValue(3);
  }
  
  public void setDailyRate(Double value) {
    setValue(4, value);
  }
  
  public Double getDailyRate() {
    return (Double)getValue(4);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row5<Integer, String, String, Double, Double> fieldsRow() {
    return (Row5<Integer, String, String, Double, Double>)super.fieldsRow();
  }
  
  public Row5<Integer, String, String, Double, Double> valuesRow() {
    return (Row5<Integer, String, String, Double, Double>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)VehicleType.VEHICLE_TYPE.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)VehicleType.VEHICLE_TYPE.UID;
  }
  
  public Field<String> field3() {
    return (Field<String>)VehicleType.VEHICLE_TYPE.NAME;
  }
  
  public Field<Double> field4() {
    return (Field<Double>)VehicleType.VEHICLE_TYPE.HOURLY_RATE;
  }
  
  public Field<Double> field5() {
    return (Field<Double>)VehicleType.VEHICLE_TYPE.DAILY_RATE;
  }
  
  public Integer value1() {
    return getId();
  }
  
  public String value2() {
    return getUid();
  }
  
  public String value3() {
    return getName();
  }
  
  public Double value4() {
    return getHourlyRate();
  }
  
  public Double value5() {
    return getDailyRate();
  }
  
  public VehicleTypeRecord() {
    super((Table)VehicleType.VEHICLE_TYPE);
  }
  
  public VehicleTypeRecord(Integer id, String uid, String name, Double hourlyRate, Double dailyRate) {
    super((Table)VehicleType.VEHICLE_TYPE);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, name);
    setValue(3, hourlyRate);
    setValue(4, dailyRate);
  }
}
