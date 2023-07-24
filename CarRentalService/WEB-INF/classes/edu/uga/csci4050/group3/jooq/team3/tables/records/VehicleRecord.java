package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.Vehicle;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row;
import org.jooq.Row10;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class VehicleRecord extends UpdatableRecordImpl<VehicleRecord> implements Record10<Integer, String, String, String, String, Integer, Integer, String, Integer, String> {
  private static final long serialVersionUID = 1780352499L;
  
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
  
  public void setType(String value) {
    setValue(2, value);
  }
  
  public String getType() {
    return (String)getValue(2);
  }
  
  public void setMake(String value) {
    setValue(3, value);
  }
  
  public String getMake() {
    return (String)getValue(3);
  }
  
  public void setModel(String value) {
    setValue(4, value);
  }
  
  public String getModel() {
    return (String)getValue(4);
  }
  
  public void setYear(Integer value) {
    setValue(5, value);
  }
  
  public Integer getYear() {
    return (Integer)getValue(5);
  }
  
  public void setMileage(Integer value) {
    setValue(6, value);
  }
  
  public Integer getMileage() {
    return (Integer)getValue(6);
  }
  
  public void setTag(String value) {
    setValue(7, value);
  }
  
  public String getTag() {
    return (String)getValue(7);
  }
  
  public void setLastservice(Integer value) {
    setValue(8, value);
  }
  
  public Integer getLastservice() {
    return (Integer)getValue(8);
  }
  
  public void setLocation(String value) {
    setValue(9, value);
  }
  
  public String getLocation() {
    return (String)getValue(9);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row10<Integer, String, String, String, String, Integer, Integer, String, Integer, String> fieldsRow() {
    return (Row10<Integer, String, String, String, String, Integer, Integer, String, Integer, String>)super.fieldsRow();
  }
  
  public Row10<Integer, String, String, String, String, Integer, Integer, String, Integer, String> valuesRow() {
    return (Row10<Integer, String, String, String, String, Integer, Integer, String, Integer, String>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)Vehicle.VEHICLE.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)Vehicle.VEHICLE.UID;
  }
  
  public Field<String> field3() {
    return (Field<String>)Vehicle.VEHICLE.TYPE;
  }
  
  public Field<String> field4() {
    return (Field<String>)Vehicle.VEHICLE.MAKE;
  }
  
  public Field<String> field5() {
    return (Field<String>)Vehicle.VEHICLE.MODEL;
  }
  
  public Field<Integer> field6() {
    return (Field<Integer>)Vehicle.VEHICLE.YEAR;
  }
  
  public Field<Integer> field7() {
    return (Field<Integer>)Vehicle.VEHICLE.MILEAGE;
  }
  
  public Field<String> field8() {
    return (Field<String>)Vehicle.VEHICLE.TAG;
  }
  
  public Field<Integer> field9() {
    return (Field<Integer>)Vehicle.VEHICLE.LASTSERVICE;
  }
  
  public Field<String> field10() {
    return (Field<String>)Vehicle.VEHICLE.LOCATION;
  }
  
  public Integer value1() {
    return getId();
  }
  
  public String value2() {
    return getUid();
  }
  
  public String value3() {
    return getType();
  }
  
  public String value4() {
    return getMake();
  }
  
  public String value5() {
    return getModel();
  }
  
  public Integer value6() {
    return getYear();
  }
  
  public Integer value7() {
    return getMileage();
  }
  
  public String value8() {
    return getTag();
  }
  
  public Integer value9() {
    return getLastservice();
  }
  
  public String value10() {
    return getLocation();
  }
  
  public VehicleRecord() {
    super((Table)Vehicle.VEHICLE);
  }
  
  public VehicleRecord(Integer id, String uid, String type, String make, String model, Integer year, Integer mileage, String tag, Integer lastservice, String location) {
    super((Table)Vehicle.VEHICLE);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, type);
    setValue(3, make);
    setValue(4, model);
    setValue(5, year);
    setValue(6, mileage);
    setValue(7, tag);
    setValue(8, lastservice);
    setValue(9, location);
  }
}
