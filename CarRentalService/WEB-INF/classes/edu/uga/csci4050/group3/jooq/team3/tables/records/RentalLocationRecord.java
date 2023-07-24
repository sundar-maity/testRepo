package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalLocationRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row;
import org.jooq.Row9;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class RentalLocationRecord extends UpdatableRecordImpl<RentalLocationRecord> implements Record9<Integer, String, String, String, String, String, Integer, String, Integer> {
  private static final long serialVersionUID = -38185727L;
  
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
  
  public void setStreetAddress(String value) {
    setValue(3, value);
  }
  
  public String getStreetAddress() {
    return (String)getValue(3);
  }
  
  public void setState(String value) {
    setValue(4, value);
  }
  
  public String getState() {
    return (String)getValue(4);
  }
  
  public void setCountry(String value) {
    setValue(5, value);
  }
  
  public String getCountry() {
    return (String)getValue(5);
  }
  
  public void setZipcode(Integer value) {
    setValue(6, value);
  }
  
  public Integer getZipcode() {
    return (Integer)getValue(6);
  }
  
  public void setCity(String value) {
    setValue(7, value);
  }
  
  public String getCity() {
    return (String)getValue(7);
  }
  
  public void setCapacity(Integer value) {
    setValue(8, value);
  }
  
  public Integer getCapacity() {
    return (Integer)getValue(8);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row9<Integer, String, String, String, String, String, Integer, String, Integer> fieldsRow() {
    return (Row9<Integer, String, String, String, String, String, Integer, String, Integer>)super.fieldsRow();
  }
  
  public Row9<Integer, String, String, String, String, String, Integer, String, Integer> valuesRow() {
    return (Row9<Integer, String, String, String, String, String, Integer, String, Integer>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)RentalLocation.RENTAL_LOCATION.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.UID;
  }
  
  public Field<String> field3() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.NAME;
  }
  
  public Field<String> field4() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.STREET_ADDRESS;
  }
  
  public Field<String> field5() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.STATE;
  }
  
  public Field<String> field6() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.COUNTRY;
  }
  
  public Field<Integer> field7() {
    return (Field<Integer>)RentalLocation.RENTAL_LOCATION.ZIPCODE;
  }
  
  public Field<String> field8() {
    return (Field<String>)RentalLocation.RENTAL_LOCATION.CITY;
  }
  
  public Field<Integer> field9() {
    return (Field<Integer>)RentalLocation.RENTAL_LOCATION.CAPACITY;
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
  
  public String value4() {
    return getStreetAddress();
  }
  
  public String value5() {
    return getState();
  }
  
  public String value6() {
    return getCountry();
  }
  
  public Integer value7() {
    return getZipcode();
  }
  
  public String value8() {
    return getCity();
  }
  
  public Integer value9() {
    return getCapacity();
  }
  
  public RentalLocationRecord() {
    super((Table)RentalLocation.RENTAL_LOCATION);
  }
  
  public RentalLocationRecord(Integer id, String uid, String name, String streetAddress, String state, String country, Integer zipcode, String city, Integer capacity) {
    super((Table)RentalLocation.RENTAL_LOCATION);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, name);
    setValue(3, streetAddress);
    setValue(4, state);
    setValue(5, country);
    setValue(6, zipcode);
    setValue(7, city);
    setValue(8, capacity);
  }
}
