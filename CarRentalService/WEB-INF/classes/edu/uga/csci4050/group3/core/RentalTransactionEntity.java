package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidInputExceptionFactory;
import edu.uga.csci4050.group3.core.RentalStatus;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RentalTransactionEntity {
  @NotNull
  @Size(min = 1)
  @Column(name = "name")
  String uid;
  
  @NotNull
  @Column(name = "start_date")
  int start_date;
  
  @NotNull
  @Column(name = "end_date")
  int end_date;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "user")
  String user;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "vehicle")
  String vehicle;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "status")
  String status;
  
  @Column(name = "comments")
  String comments;
  
  public RentalTransactionEntity() {
    this.uid = UUID.randomUUID().toString();
    this.start_date = 0;
    this.end_date = 0;
    this.user = "Unknown";
    this.vehicle = "Unknown";
    setStatusEnum(RentalStatus.ACTIVE);
    this.comments = "None";
  }
  
  @Column(name = "uid")
  public String getUid() {
    return this.uid;
  }
  
  @Column(name = "uid")
  public void setUid(String uid) {
    this.uid = uid;
  }
  
  @Column(name = "start_date")
  public int getStart_date() {
    return this.start_date;
  }
  
  public Date getStart_dateDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.start_date);
  }
  
  @Column(name = "start_date")
  public void setStart_date(int start_date) {
    this.start_date = start_date;
  }
  
  public void setStart_dateDate(Date start_date) {
    this.start_date = DatabaseAbstraction.getTimestampFromDate(start_date);
  }
  
  @Column(name = "end_date")
  public int getEnd_date() {
    return this.end_date;
  }
  
  public Date getEnd_dateDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.end_date);
  }
  
  @Column(name = "end_date")
  public void setEnd_date(int end_date) {
    this.end_date = end_date;
  }
  
  public void setEnd_dateDate(Date end_date) {
    this.end_date = DatabaseAbstraction.getTimestampFromDate(end_date);
  }
  
  @Column(name = "user")
  public String getUser() {
    return this.user;
  }
  
  @Column(name = "user")
  public void setUser(String user) {
    this.user = user;
  }
  
  @Column(name = "vehicle")
  public String getVehicle() {
    return this.vehicle;
  }
  
  @Column(name = "vehicle")
  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }
  
  @Column(name = "status")
  public String getStatus() {
    return this.status;
  }
  
  public RentalStatus getStatusEnum() {
    return RentalStatus.valueOf(this.status);
  }
  
  @Column(name = "status")
  public void setStatus(String status) {
    this.status = status;
  }
  
  public void setStatusEnum(RentalStatus status) {
    this.status = status.toString();
  }
  
  @Column(name = "comments")
  public String getComments() {
    return this.comments;
  }
  
  @Column(name = "comments")
  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public void validate() throws InvalidInputException {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();
    Set<ConstraintViolation<edu.uga.csci4050.group3.core.RentalTransactionEntity>> constraintViolations = validator.validate(this, new Class[0]);
    if (constraintViolations.size() > 0) {
      InvalidInputExceptionFactory<edu.uga.csci4050.group3.core.RentalTransactionEntity> iief = new InvalidInputExceptionFactory();
      throw iief.buildException(constraintViolations);
    } 
  }
  
  public Map<String, String> getData(boolean humanDates) {
    Map<String, String> data = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    if (humanDates == true)
      sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
    data.put("uid", this.uid);
    data.put("user", this.user);
    try {
      data.put("username", DatabaseAbstraction.getUser(this.user).getUsername());
    } catch (RecordNotFoundException e) {
      e.printStackTrace();
    } 
    data.put("start_date", sdf.format(getStart_dateDate()));
    data.put("end_date", sdf.format(getEnd_dateDate()));
    data.put("vehicle", this.vehicle);
    data.put("status", this.status);
    data.put("comments", this.comments);
    return data;
  }
}
