package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3.tables.records;

import edu.uga.csci4050.group3.jooq.team3.tables.User;
import edu.uga.csci4050.group3.jooq.team3.tables.records.UserRecord;
import org.jooq.Field;
import org.jooq.Record;
import org.jooq.Record1;
import org.jooq.Record16;
import org.jooq.Row;
import org.jooq.Row16;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;

public class UserRecord extends UpdatableRecordImpl<UserRecord> implements Record16<Integer, String, String, String, String, String, String, String, String, Integer, String, String, String, Integer, String, Integer> {
  private static final long serialVersionUID = -890885618L;
  
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
  
  public void setUsername(String value) {
    setValue(2, value);
  }
  
  public String getUsername() {
    return (String)getValue(2);
  }
  
  public void setPassword(String value) {
    setValue(3, value);
  }
  
  public String getPassword() {
    return (String)getValue(3);
  }
  
  public void setEmail(String value) {
    setValue(4, value);
  }
  
  public String getEmail() {
    return (String)getValue(4);
  }
  
  public void setFirstName(String value) {
    setValue(5, value);
  }
  
  public String getFirstName() {
    return (String)getValue(5);
  }
  
  public void setLastName(String value) {
    setValue(6, value);
  }
  
  public String getLastName() {
    return (String)getValue(6);
  }
  
  public void setRole(String value) {
    setValue(7, value);
  }
  
  public String getRole() {
    return (String)getValue(7);
  }
  
  public void setLicense(String value) {
    setValue(8, value);
  }
  
  public String getLicense() {
    return (String)getValue(8);
  }
  
  public void setDateofbirth(Integer value) {
    setValue(9, value);
  }
  
  public Integer getDateofbirth() {
    return (Integer)getValue(9);
  }
  
  public void setStreetAddress(String value) {
    setValue(10, value);
  }
  
  public String getStreetAddress() {
    return (String)getValue(10);
  }
  
  public void setState(String value) {
    setValue(11, value);
  }
  
  public String getState() {
    return (String)getValue(11);
  }
  
  public void setCountry(String value) {
    setValue(12, value);
  }
  
  public String getCountry() {
    return (String)getValue(12);
  }
  
  public void setZipcode(Integer value) {
    setValue(13, value);
  }
  
  public Integer getZipcode() {
    return (Integer)getValue(13);
  }
  
  public void setCity(String value) {
    setValue(14, value);
  }
  
  public String getCity() {
    return (String)getValue(14);
  }
  
  public void setMembershipExpiration(Integer value) {
    setValue(15, value);
  }
  
  public Integer getMembershipExpiration() {
    return (Integer)getValue(15);
  }
  
  public Record1<Integer> key() {
    return (Record1<Integer>)super.key();
  }
  
  public Row16<Integer, String, String, String, String, String, String, String, String, Integer, String, String, String, Integer, String, Integer> fieldsRow() {
    return (Row16<Integer, String, String, String, String, String, String, String, String, Integer, String, String, String, Integer, String, Integer>)super.fieldsRow();
  }
  
  public Row16<Integer, String, String, String, String, String, String, String, String, Integer, String, String, String, Integer, String, Integer> valuesRow() {
    return (Row16<Integer, String, String, String, String, String, String, String, String, Integer, String, String, String, Integer, String, Integer>)super.valuesRow();
  }
  
  public Field<Integer> field1() {
    return (Field<Integer>)User.USER.ID;
  }
  
  public Field<String> field2() {
    return (Field<String>)User.USER.UID;
  }
  
  public Field<String> field3() {
    return (Field<String>)User.USER.USERNAME;
  }
  
  public Field<String> field4() {
    return (Field<String>)User.USER.PASSWORD;
  }
  
  public Field<String> field5() {
    return (Field<String>)User.USER.EMAIL;
  }
  
  public Field<String> field6() {
    return (Field<String>)User.USER.FIRST_NAME;
  }
  
  public Field<String> field7() {
    return (Field<String>)User.USER.LAST_NAME;
  }
  
  public Field<String> field8() {
    return (Field<String>)User.USER.ROLE;
  }
  
  public Field<String> field9() {
    return (Field<String>)User.USER.LICENSE;
  }
  
  public Field<Integer> field10() {
    return (Field<Integer>)User.USER.DATEOFBIRTH;
  }
  
  public Field<String> field11() {
    return (Field<String>)User.USER.STREET_ADDRESS;
  }
  
  public Field<String> field12() {
    return (Field<String>)User.USER.STATE;
  }
  
  public Field<String> field13() {
    return (Field<String>)User.USER.COUNTRY;
  }
  
  public Field<Integer> field14() {
    return (Field<Integer>)User.USER.ZIPCODE;
  }
  
  public Field<String> field15() {
    return (Field<String>)User.USER.CITY;
  }
  
  public Field<Integer> field16() {
    return (Field<Integer>)User.USER.MEMBERSHIP_EXPIRATION;
  }
  
  public Integer value1() {
    return getId();
  }
  
  public String value2() {
    return getUid();
  }
  
  public String value3() {
    return getUsername();
  }
  
  public String value4() {
    return getPassword();
  }
  
  public String value5() {
    return getEmail();
  }
  
  public String value6() {
    return getFirstName();
  }
  
  public String value7() {
    return getLastName();
  }
  
  public String value8() {
    return getRole();
  }
  
  public String value9() {
    return getLicense();
  }
  
  public Integer value10() {
    return getDateofbirth();
  }
  
  public String value11() {
    return getStreetAddress();
  }
  
  public String value12() {
    return getState();
  }
  
  public String value13() {
    return getCountry();
  }
  
  public Integer value14() {
    return getZipcode();
  }
  
  public String value15() {
    return getCity();
  }
  
  public Integer value16() {
    return getMembershipExpiration();
  }
  
  public UserRecord() {
    super((Table)User.USER);
  }
  
  public UserRecord(Integer id, String uid, String username, String password, String email, String firstName, String lastName, String role, String license, Integer dateofbirth, String streetAddress, String state, String country, Integer zipcode, String city, Integer membershipExpiration) {
    super((Table)User.USER);
    setValue(0, id);
    setValue(1, uid);
    setValue(2, username);
    setValue(3, password);
    setValue(4, email);
    setValue(5, firstName);
    setValue(6, lastName);
    setValue(7, role);
    setValue(8, license);
    setValue(9, dateofbirth);
    setValue(10, streetAddress);
    setValue(11, state);
    setValue(12, country);
    setValue(13, zipcode);
    setValue(14, city);
    setValue(15, membershipExpiration);
  }
}
