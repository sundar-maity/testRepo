package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidInputExceptionFactory;
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

public class LocationEntity {
  @NotNull
  @Size(min = 1)
  @Column(name = "name")
  String name = "Unknown";
  
  @NotNull
  @Column(name = "capacity")
  int capacity = 1;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "uid")
  String uid = UUID.randomUUID().toString();
  
  @NotNull
  @Size(min = 1)
  @Column(name = "country")
  String country = "Unknown";
  
  @NotNull
  @Size(min = 1)
  @Column(name = "state")
  String state = "Unknown";
  
  @NotNull
  @Size(min = 1)
  @Column(name = "city")
  String city = "Unknown";
  
  @NotNull
  @Column(name = "zipcode")
  int zipcode = 10000;
  
  @NotNull
  @Size(min = 1)
  @Column(name = "street_address")
  String street_address = "Unknown";
  
  @Column(name = "uid")
  public String getUid() {
    return this.uid;
  }
  
  @Column(name = "uid")
  public void setUid(String uid) {
    this.uid = uid;
  }
  
  @Column(name = "name")
  public String getName() {
    return this.name;
  }
  
  @Column(name = "name")
  public void setName(String name) {
    this.name = name;
  }
  
  @Column(name = "street_address")
  public String getStreet_address() {
    return this.street_address;
  }
  
  @Column(name = "street_address")
  public void setStreet_address(String street_address) {
    this.street_address = street_address;
  }
  
  @Column(name = "state")
  public String getState() {
    return this.state;
  }
  
  @Column(name = "state")
  public void setState(String state) {
    this.state = state;
  }
  
  @Column(name = "country")
  public String getCountry() {
    return this.country;
  }
  
  @Column(name = "country")
  public void setCountry(String country) {
    this.country = country;
  }
  
  @Column(name = "zipcode")
  public int getZipcode() {
    return this.zipcode;
  }
  
  @Column(name = "zipcode")
  public void setZipcode(int zipcode) {
    this.zipcode = zipcode;
  }
  
  @Column(name = "city")
  public String getCity() {
    return this.city;
  }
  
  @Column(name = "city")
  public void setCity(String city) {
    this.city = city;
  }
  
  @Column(name = "capacity")
  public int getCapacity() {
    return this.capacity;
  }
  
  @Column(name = "capacity")
  public void setCapacity(int capacity) {
    this.capacity = capacity;
  }
  
  public void loadFromForm(Map<String, String[]> formData) {
    if (formData.containsKey("locationName"))
      setName(((String[])formData.get("locationName"))[0]); 
    if (formData.containsKey("locationAddress"))
      setStreet_address(((String[])formData.get("locationAddress"))[0]); 
    if (formData.containsKey("locationState"))
      setState(((String[])formData.get("locationState"))[0]); 
    if (formData.containsKey("locationCountry"))
      setCountry(((String[])formData.get("locationCountry"))[0]); 
    if (formData.containsKey("locationZipcode"))
      setZipcode((new Integer(((String[])formData.get("locationZipcode"))[0])).intValue()); 
    if (formData.containsKey("locationCity"))
      setCity(((String[])formData.get("locationCity"))[0]); 
    if (formData.containsKey("locationCapacity"))
      setCapacity((new Integer(((String[])formData.get("locationCapacity"))[0])).intValue()); 
  }
  
  public void validate() throws InvalidInputException {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();
    Set<ConstraintViolation<edu.uga.csci4050.group3.core.LocationEntity>> constraintViolations = validator.validate(this, new Class[0]);
    if (constraintViolations.size() > 0) {
      InvalidInputExceptionFactory<edu.uga.csci4050.group3.core.LocationEntity> iief = new InvalidInputExceptionFactory();
      throw iief.buildException(constraintViolations);
    } 
  }
  
  public Map<String, String> getData() {
    Map<String, String> data = new HashMap<>();
    data.put("uid", this.uid);
    data.put("name", this.name);
    data.put("street_address", this.street_address);
    data.put("state", this.state);
    data.put("country", this.country);
    data.put("zipcode", String.valueOf(this.zipcode));
    data.put("city", this.city);
    data.put("capacity", String.valueOf(this.capacity));
    return data;
  }
}
