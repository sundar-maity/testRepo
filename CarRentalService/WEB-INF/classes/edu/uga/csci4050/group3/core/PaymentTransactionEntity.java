package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidInputExceptionFactory;
import edu.uga.csci4050.group3.core.PaymentReason;
import edu.uga.csci4050.group3.core.UserEntity;
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

public class PaymentTransactionEntity {
  @NotNull
  @Size(min = 1)
  @Column(name = "uid")
  String uid = UUID.randomUUID().toString();
  
  @NotNull
  @Column(name = "date")
  int date = 0;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "method")
  String method = "Unknown";
  
  @NotNull
  @Size(min = 1)
  @Column(name = "description")
  String description = "Unknown";
  
  @NotNull
  @Size(min = 1)
  @Column(name = "user")
  String user = "Unknown";
  
  @NotNull
  @Size(min = 1)
  @Column(name = "reason")
  String reason = "Unknown";
  
  public String getUid() {
    return this.uid;
  }
  
  public void setUid(String uid) {
    this.uid = uid;
  }
  
  public int getDate() {
    return this.date;
  }
  
  public Date getDateDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.date);
  }
  
  public void setDate(int date) {
    this.date = date;
  }
  
  public void setDateDate(Date date) {
    this.date = DatabaseAbstraction.getTimestampFromDate(date);
  }
  
  public String getMethod() {
    return this.method;
  }
  
  public void setMethod(String method) {
    this.method = method;
  }
  
  public String getDescription() {
    return this.description;
  }
  
  public void setDescription(String description) {
    this.description = description;
  }
  
  public String getUser() {
    return this.user;
  }
  
  public void setUser(String user) {
    this.user = user;
  }
  
  public String getReason() {
    return this.reason;
  }
  
  public PaymentReason getReasonEnum() {
    return PaymentReason.valueOf(this.reason);
  }
  
  public void setReason(String reason) {
    this.reason = reason;
  }
  
  public void setReasonFromEnum(PaymentReason reason) {
    this.reason = reason.name();
  }
  
  public void validate() throws InvalidInputException {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();
    Set<ConstraintViolation<edu.uga.csci4050.group3.core.PaymentTransactionEntity>> constraintViolations = validator.validate(this, new Class[0]);
    if (constraintViolations.size() > 0) {
      InvalidInputExceptionFactory<edu.uga.csci4050.group3.core.PaymentTransactionEntity> iief = new InvalidInputExceptionFactory();
      throw iief.buildException(constraintViolations);
    } 
  }
  
  public Map<String, String> getData() {
    Map<String, String> data = new HashMap<>();
    SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    try {
      UserEntity user = DatabaseAbstraction.getUser(getUser());
      data.put("username", user.getUsername());
    } catch (RecordNotFoundException e) {
      data.put("username", "Unavailable");
    } 
    data.put("description", this.description);
    data.put("reason", this.reason);
    data.put("method", this.method);
    data.put("date", sdftime.format(DatabaseAbstraction.getDateFromTimestamp(this.date)));
    return data;
  }
}
