package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalTransactionRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row;
import org.jooq.Row8;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class RentalTransactionRecord extends UpdatableRecordImpl<RentalTransactionRecord> implements Record8<Integer, String, Integer, Integer, String, String, String, String> {
  private static final long serialVersionUID = -905116817L;
  
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
  
  public void setStartDate(Integer value) {
    setValue(2, value);
  }
  
  public Integer getStartDate() {
    return (Integer)getValue(2);
  }
  
  public void setEndDate(Integer value) {
    setValue(3, value);
  }
  
  public Integer getEndDate() {
    return (Integer)getValue(3);
  }
  
  public void setUser(String value) {
    setValue(4, value);
  }
  
  public String getUser() {
    return (String)getValue(4);
  }
  
  public void setVehicle(String value) {
    setValue(5, value);
  }
  
  public String getVehicle() {
    return (String)getValue(5);
  }
  
  public void setStatus(String value) {
    setValue(6, value);
  }
  
  public String getStatus() {
    return (String)getValue(6);
  }
  
  public void setComments(String value) {
    setValue(7, value);
  }
  
  public String getComments() {
    return (String)getValue(7);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row8<Integer, String, Integer, Integer, String, String, String, String> fieldsRow() {
    return (Row8<Integer, String, Integer, Integer, String, String, String, String>)super.fieldsRow();
  }
  
  public Row8<Integer, String, Integer, Integer, String, String, String, String> valuesRow() {
    return (Row8<Integer, String, Integer, Integer, String, String, String, String>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)RentalTransaction.RENTAL_TRANSACTION.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)RentalTransaction.RENTAL_TRANSACTION.UID;
  }
  
  public Field<Integer> field3() {
    return (Field<Integer>)RentalTransaction.RENTAL_TRANSACTION.START_DATE;
  }
  
  public Field<Integer> field4() {
    return (Field<Integer>)RentalTransaction.RENTAL_TRANSACTION.END_DATE;
  }
  
  public Field<String> field5() {
    return (Field<String>)RentalTransaction.RENTAL_TRANSACTION.USER;
  }
  
  public Field<String> field6() {
    return (Field<String>)RentalTransaction.RENTAL_TRANSACTION.VEHICLE;
  }
  
  public Field<String> field7() {
    return (Field<String>)RentalTransaction.RENTAL_TRANSACTION.STATUS;
  }
  
  public Field<String> field8() {
    return (Field<String>)RentalTransaction.RENTAL_TRANSACTION.COMMENTS;
  }
  
  public Integer value1() {
    return getId();
  }
  
  public String value2() {
    return getUid();
  }
  
  public Integer value3() {
    return getStartDate();
  }
  
  public Integer value4() {
    return getEndDate();
  }
  
  public String value5() {
    return getUser();
  }
  
  public String value6() {
    return getVehicle();
  }
  
  public String value7() {
    return getStatus();
  }
  
  public String value8() {
    return getComments();
  }
  
  public RentalTransactionRecord() {
    super((Table)RentalTransaction.RENTAL_TRANSACTION);
  }
  
  public RentalTransactionRecord(Integer id, String uid, Integer startDate, Integer endDate, String user, String vehicle, String status, String comments) {
    super((Table)RentalTransaction.RENTAL_TRANSACTION);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, startDate);
    setValue(3, endDate);
    setValue(4, user);
    setValue(5, vehicle);
    setValue(6, status);
    setValue(7, comments);
  }
}
