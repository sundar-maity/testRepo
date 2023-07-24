package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.records.PaymentTransactionRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record9;
import org.jooq.Row;
import org.jooq.Row9;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class PaymentTransactionRecord extends UpdatableRecordImpl<PaymentTransactionRecord> implements Record9<Integer, String, Integer, String, String, String, String, String, String> {
  private static final long serialVersionUID = -401676541L;
  
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
  
  public void setDate(Integer value) {
    setValue(2, value);
  }
  
  public Integer getDate() {
    return (Integer)getValue(2);
  }
  
  public void setMethod(String value) {
    setValue(3, value);
  }
  
  public String getMethod() {
    return (String)getValue(3);
  }
  
  public void setDescription(String value) {
    setValue(4, value);
  }
  
  public String getDescription() {
    return (String)getValue(4);
  }
  
  public void setUser(String value) {
    setValue(5, value);
  }
  
  public String getUser() {
    return (String)getValue(5);
  }
  
  public void setReason(String value) {
    setValue(6, value);
  }
  
  public String getReason() {
    return (String)getValue(6);
  }
  
  public void setComments(String value) {
    setValue(7, value);
  }
  
  public String getComments() {
    return (String)getValue(7);
  }
  
  public void setStatus(String value) {
    setValue(8, value);
  }
  
  public String getStatus() {
    return (String)getValue(8);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row9<Integer, String, Integer, String, String, String, String, String, String> fieldsRow() {
    return (Row9<Integer, String, Integer, String, String, String, String, String, String>)super.fieldsRow();
  }
  
  public Row9<Integer, String, Integer, String, String, String, String, String, String> valuesRow() {
    return (Row9<Integer, String, Integer, String, String, String, String, String, String>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)PaymentTransaction.PAYMENT_TRANSACTION.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.UID;
  }
  
  public Field<Integer> field3() {
    return (Field<Integer>)PaymentTransaction.PAYMENT_TRANSACTION.DATE;
  }
  
  public Field<String> field4() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.METHOD;
  }
  
  public Field<String> field5() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.DESCRIPTION;
  }
  
  public Field<String> field6() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.USER;
  }
  
  public Field<String> field7() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.REASON;
  }
  
  public Field<String> field8() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.COMMENTS;
  }
  
  public Field<String> field9() {
    return (Field<String>)PaymentTransaction.PAYMENT_TRANSACTION.STATUS;
  }
  
  public Integer value1() {
    return getId();
  }
  
  public String value2() {
    return getUid();
  }
  
  public Integer value3() {
    return getDate();
  }
  
  public String value4() {
    return getMethod();
  }
  
  public String value5() {
    return getDescription();
  }
  
  public String value6() {
    return getUser();
  }
  
  public String value7() {
    return getReason();
  }
  
  public String value8() {
    return getComments();
  }
  
  public String value9() {
    return getStatus();
  }
  
  public PaymentTransactionRecord() {
    super((Table)PaymentTransaction.PAYMENT_TRANSACTION);
  }
  
  public PaymentTransactionRecord(Integer id, String uid, Integer date, String method, String description, String user, String reason, String comments, String status) {
    super((Table)PaymentTransaction.PAYMENT_TRANSACTION);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, date);
    setValue(3, method);
    setValue(4, description);
    setValue(5, user);
    setValue(6, reason);
    setValue(7, comments);
    setValue(8, status);
  }
}
