package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidInputExceptionFactory;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.persistence.Column;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class VehicleEntity {
  @Column(name = "uid")
  @Pattern(regexp = "[a-zA-Z0-9\\-]+")
  @NotNull
  @Size(min = 1)
  String uid = UUID.randomUUID().toString();
  
  @Column(name = "year")
  @NotNull
  @Min(0L)
  int year = 2013;
  
  @Column(name = "model")
  @NotNull
  @Size(min = 1)
  String model = "Unknown";
  
  @Column(name = "type")
  @NotNull
  @Size(min = 1)
  @Pattern(regexp = "[a-zA-Z0-9\\-]+")
  String type = "Unknown";
  
  @Column(name = "tag")
  @NotNull
  @Size(min = 1)
  String tag = "000000";
  
  @Column(name = "mileage")
  @NotNull
  @Min(0L)
  int mileage = 0;
  
  @Column(name = "lastservice")
  @NotNull
  int lastservice = 0;
  
  @Column(name = "location")
  @Pattern(regexp = "[a-zA-Z0-9\\-]+")
  @NotNull
  @Size(min = 1)
  String location = "Unknown";
  
  @Column(name = "make")
  @NotNull
  @Size(min = 1)
  String make = "Unknown";
  
  public String getUid() {
    return this.uid;
  }
  
  public void setUid(String uid) {
    this.uid = uid;
  }
  
  public String getType() {
    return this.type;
  }
  
  public void setType(String type) {
    this.type = type;
  }
  
  public String getMake() {
    return this.make;
  }
  
  public void setMake(String make) {
    this.make = make;
  }
  
  public String getModel() {
    return this.model;
  }
  
  public void setModel(String model) {
    this.model = model;
  }
  
  public int getYear() {
    return this.year;
  }
  
  public void setYear(int year) {
    this.year = year;
  }
  
  public String getTag() {
    return this.tag;
  }
  
  public void setTag(String tag) {
    this.tag = tag;
  }
  
  public int getMileage() {
    return this.mileage;
  }
  
  public void setMileage(int mileage) {
    this.mileage = mileage;
  }
  
  public int getLastservice() {
    return this.lastservice;
  }
  
  public Date getLastserviceDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.lastservice);
  }
  
  public void setLastservice(int lastservice) {
    this.lastservice = lastservice;
  }
  
  public void setLastserviceDate(Date lastservice) {
    this.lastservice = DatabaseAbstraction.getTimestampFromDate(lastservice);
  }
  
  public String getLocation() {
    return this.location;
  }
  
  public void setLocation(String location) {
    this.location = location;
  }
  
  public void loadFromForm(Map<String, String[]> map) throws InvalidInputException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    InvalidInputException exception = new InvalidInputException();
    if (map.containsKey("vehicleModel"))
      setModel(((String[])map.get("vehicleModel"))[0]); 
    if (map.containsKey("vehicleMake"))
      setMake(((String[])map.get("vehicleMake"))[0]); 
    if (map.containsKey("vehicleYear"))
      setYear((new Integer(((String[])map.get("vehicleYear"))[0])).intValue()); 
    if (map.containsKey("vehicleTag"))
      setTag(((String[])map.get("vehicleTag"))[0]); 
    if (map.containsKey("vehicleMileage"))
      setMileage((new Integer(((String[])map.get("vehicleMileage"))[0])).intValue()); 
    if (map.containsKey("vehicleDate"))
      try {
        setLastserviceDate(sdf.parse(((String[])map.get("vehicleDate"))[0]));
      } catch (ParseException e) {
        exception.addMessage("Invalid date format for the service date");
      }  
    if (map.containsKey("vehicleType"))
      setType(((String[])map.get("vehicleType"))[0]); 
    if (map.containsKey("vehicleLocation"))
      setLocation(((String[])map.get("vehicleLocation"))[0]); 
    if (exception.countMessages() > 0)
      throw exception; 
  }
  
  public void validate() throws InvalidInputException {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();
    Set<ConstraintViolation<edu.uga.csci4050.group3.core.VehicleEntity>> constraintViolations = validator.validate(this, new Class[0]);
    if (constraintViolations.size() > 0) {
      InvalidInputExceptionFactory<edu.uga.csci4050.group3.core.VehicleEntity> iief = new InvalidInputExceptionFactory();
      throw iief.buildException(constraintViolations);
    } 
  }
  
  public Map<String, String> getCustomerData() {
    Map<String, String> data = new HashMap<>();
    data.put("uid", this.uid);
    data.put("model", this.model);
    data.put("make", this.make);
    data.put("year", String.valueOf(this.year));
    try {
      String temp_type = DatabaseAbstraction.getVehicleType(this.type).getName();
      data.put("type", temp_type);
    } catch (RecordNotFoundException e) {
      data.put("type", this.type);
    } 
    data.put("type_uid", this.type);
    try {
      String temp_location = DatabaseAbstraction.getLocation(this.location).getName();
      data.put("location", temp_location);
    } catch (RecordNotFoundException e) {
      data.put("location", this.location);
    } 
    data.put("location_uid", this.location);
    return data;
  }
  
  public Map<String, String> getAdminData() {
    Map<String, String> data = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    data.put("uid", this.uid);
    data.put("model", this.model);
    data.put("make", this.make);
    data.put("year", String.valueOf(this.year));
    data.put("type", this.type);
    data.put("lastservice", sdf.format(Integer.valueOf(this.lastservice)));
    data.put("tag", this.tag);
    data.put("mileage", String.valueOf(this.mileage));
    data.put("location", this.location);
    data.put("location_uid", this.location);
    return data;
  }
}
