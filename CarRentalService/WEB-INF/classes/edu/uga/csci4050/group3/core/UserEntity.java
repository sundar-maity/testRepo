package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidInputExceptionFactory;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserEntity {
  @Column(name = "uid")
  @Size(min = 1)
  @NotNull(message = "UID should not be empty")
  String uid;
  
  @Column(name = "username")
  @Size(min = 5, message = "Username should be at least 5 characters long")
  @NotNull(message = "Username should not be empty")
  @Pattern(regexp = "[a-zA-Z0-9-_.]{8,32}", message = "Usernames can only contian alphanumerical characters and dashes")
  String username;
  
  @Column(name = "password")
  @Size(min = 8, max = 250, message = "Passwords should be anywhere between 8 and 250 characters")
  @NotNull(message = "Password should not be empty")
  String password;
  
  @Column(name = "email")
  @Size(min = 1)
  @NotNull(message = "Email should not be empty")
  String email;
  
  @Column(name = "first_name")
  @Size(min = 1)
  @NotNull(message = "First name should not be empty")
  String first_name;
  
  @Column(name = "last_name")
  @Size(min = 1)
  @NotNull(message = "Last name should not be empty")
  String last_name;
  
  @Column(name = "role")
  @Size(min = 1)
  @NotNull(message = "Role should not be empty")
  String role;
  
  @Column(name = "license")
  @Size(min = 1)
  @NotNull(message = "License should not be empty")
  String license;
  
  @Column(name = "dateofbirth")
  @NotNull(message = "Date of birth should not be empty")
  int dateofbirth;
  
  @Column(name = "street_address")
  @Size(min = 1)
  @NotNull(message = "Address should not be empty")
  String address;
  
  @Column(name = "country")
  @Size(min = 1)
  @NotNull(message = "Country should not be empty")
  String country;
  
  @Column(name = "zipcode")
  @NotNull(message = "Zipcode should not be empty")
  int zipcode;
  
  @Column(name = "city")
  @Size(min = 1)
  @NotNull(message = "City should not be empty")
  String city;
  
  @Column(name = "state")
  @Size(min = 1)
  @NotNull(message = "State should not be empty")
  String state;
  
  @Column(name = "membership_expiration")
  @NotNull(message = "Membership expiration should not be empty")
  int membership_expiration;
  
  public UserEntity() {
    this.uid = UUID.randomUUID().toString();
    setRoleFromEnum(UserType.CUSTOMER);
    this.first_name = "test";
    this.membership_expiration = 0;
  }
  
  @Column(name = "uid")
  public String getUid() {
    return this.uid;
  }
  
  @Column(name = "uid")
  public void setUid(String uid) {
    this.uid = uid;
  }
  
  @Column(name = "username")
  public String getUsername() {
    return this.username;
  }
  
  @Column(name = "username")
  public void setUsername(String username) {
    this.username = username;
  }
  
  @Column(name = "password")
  public String getPassword() {
    return this.password;
  }
  
  @Column(name = "password")
  public void setPassword(String password) {
    this.password = password;
  }
  
  @Column(name = "email")
  public String getEmail() {
    return this.email;
  }
  
  @Column(name = "email")
  public void setEmail(String email) {
    this.email = email;
  }
  
  @Column(name = "first_name")
  public String getFirst_name() {
    return this.first_name;
  }
  
  @Column(name = "first_name")
  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }
  
  @Column(name = "last_name")
  public String getLast_name() {
    return this.last_name;
  }
  
  @Column(name = "last_name")
  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }
  
  @Column(name = "role")
  public String getRole() {
    return this.role;
  }
  
  public UserType getRoleEnum() {
    return UserType.valueOf(this.role);
  }
  
  @Column(name = "role")
  public void setRole(String role) {
    this.role = role;
  }
  
  public void setRoleFromEnum(UserType role) {
    this.role = role.name();
  }
  
  @Column(name = "license")
  public String getLicense() {
    return this.license;
  }
  
  @Column(name = "license")
  public void setLicense(String license) {
    this.license = license;
  }
  
  @Column(name = "dateofbirth")
  public int getDateofbirth() {
    return this.dateofbirth;
  }
  
  public Date getDateofbirthDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.dateofbirth);
  }
  
  @Column(name = "dateofbirth")
  public void setDateofbirth(int dateofbirth) {
    this.dateofbirth = dateofbirth;
  }
  
  public void setDateofbirthDate(Date dateofbirth) {
    this.dateofbirth = DatabaseAbstraction.getTimestampFromDate(dateofbirth);
  }
  
  @Column(name = "street_address")
  public String getStreet_Address() {
    return this.address;
  }
  
  @Column(name = "street_address")
  public void setStreet_Address(String address) {
    this.address = address;
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
  
  @Column(name = "state")
  public String getState() {
    return this.state;
  }
  
  @Column(name = "state")
  public void setState(String state) {
    this.state = state;
  }
  
  @Column(name = "membership_expiration")
  public int getMembershipExpiration() {
    return this.membership_expiration;
  }
  
  public Date getMembershipExpirationDate() {
    return DatabaseAbstraction.getDateFromTimestamp(this.membership_expiration);
  }
  
  @Column(name = "membership_expiration")
  public void setMembershipExpiration(int membership_expiration) {
    this.membership_expiration = membership_expiration;
  }
  
  public void setMembershipExpirationDate(Date membership_expiration) {
    this.membership_expiration = DatabaseAbstraction.getTimestampFromDate(membership_expiration);
  }
  
  public void loadFromForm(Map<String, String[]> formData) throws InvalidInputException {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    InvalidInputException exception = new InvalidInputException();
    if (formData.containsKey("userUsername"))
      setUsername(((String[])formData.get("userUsername"))[0]); 
    if (formData.containsKey("userPassword"))
      setPassword(((String[])formData.get("userPassword"))[0]); 
    if (formData.containsKey("userEmail"))
      setEmail(((String[])formData.get("userEmail"))[0]); 
    if (formData.containsKey("userFirstName"))
      setFirst_name(((String[])formData.get("userFirstName"))[0]); 
    if (formData.containsKey("userLastName"))
      setLast_name(((String[])formData.get("userLastName"))[0]); 
    if (formData.containsKey("userRole"))
      setRole(((String[])formData.get("userRole"))[0]); 
    if (formData.containsKey("userLicense"))
      setLicense(((String[])formData.get("userLicense"))[0]); 
    if (formData.containsKey("userDateOfBirth"))
      try {
        setDateofbirthDate(sdf.parse(((String[])formData.get("userDateOfBirth"))[0]));
      } catch (ParseException e) {
        exception.addMessage("Invalid date format for date of birth");
      }  
    if (formData.containsKey("userAddress"))
      setStreet_Address(((String[])formData.get("userAddress"))[0]); 
    if (formData.containsKey("userCountry"))
      setCountry(((String[])formData.get("userCountry"))[0]); 
    if (formData.containsKey("userZipcode"))
      setZipcode((new Integer(((String[])formData.get("userZipcode"))[0])).intValue()); 
    if (formData.containsKey("userCity"))
      setCity(((String[])formData.get("userCity"))[0]); 
    if (formData.containsKey("userState"))
      setState(((String[])formData.get("userState"))[0]); 
    if (formData.containsKey("userMembershipExpiration"))
      try {
        setDateofbirthDate(sdftime.parse(((String[])formData.get("userMembershipExpiration"))[0]));
      } catch (ParseException e) {
        exception.addMessage("Invalid date format for membership expiration");
      }  
    if (exception.countMessages() > 0)
      throw exception; 
  }
  
  public void validate() throws InvalidInputException {
    ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    Validator validator = vf.getValidator();
    Set<ConstraintViolation<edu.uga.csci4050.group3.core.UserEntity>> constraintViolations = validator.validate(this, new Class[0]);
    if (constraintViolations.size() > 0) {
      InvalidInputExceptionFactory<edu.uga.csci4050.group3.core.UserEntity> iief = new InvalidInputExceptionFactory();
      throw iief.buildException(constraintViolations);
    } 
  }
  
  public Map<String, String> getData() {
    Map<String, String> data = new HashMap<>();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat sdftime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    data.put("uid", this.uid);
    data.put("username", this.username);
    data.put("first_name", this.first_name);
    data.put("last_name", this.last_name);
    data.put("email", this.email);
    data.put("password", this.password);
    data.put("address", this.address);
    data.put("state", this.state);
    data.put("city", this.city);
    data.put("country", this.country);
    data.put("license", this.license);
    data.put("role", getRoleEnum().toString());
    data.put("dateofbirth", sdf.format(Integer.valueOf(this.dateofbirth)));
    data.put("zipcode", String.valueOf(this.zipcode));
    data.put("membership_expiration", sdftime.format(Integer.valueOf(this.membership_expiration)));
    return data;
  }
}
