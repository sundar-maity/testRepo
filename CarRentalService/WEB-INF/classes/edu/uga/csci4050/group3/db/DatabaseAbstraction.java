package WEB-INF.classes.edu.uga.csci4050.group3.db;

import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.LocationEntity;
import edu.uga.csci4050.group3.core.PaymentTransactionEntity;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.User;
import edu.uga.csci4050.group3.jooq.team3.tables.Vehicle;
import edu.uga.csci4050.group3.jooq.team3.tables.VehicleType;
import edu.uga.csci4050.group3.jooq.team3.tables.records.PaymentTransactionRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalLocationRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalTransactionRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.UserRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleTypeRecord;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.SQLDialect;
import org.jooq.Table;
import org.jooq.TableLike;
import org.jooq.TableRecord;
import org.jooq.impl.DSL;

public class DatabaseAbstraction {
  private static final String USERNAME = System.getenv().getOrDefault("DB_USERNAME", "root");
  
  private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "root");
  
  private static final String DB_URL = System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost:3306/car_rental_ms");
  
  public static Connection getConnection() {
    Connection conn = null;
    try {
      Class.forName("com.mysql.jdbc.Driver").newInstance();
      conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
      System.out.println("formed DB_URL--------" + DB_URL);
      return conn;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    } 
  }
  
  public static VehicleEntity getVehicle(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<VehicleEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)Vehicle.VEHICLE }).where(new Condition[] { Vehicle.VEHICLE.UID.equal(UID) }).fetch().into(VehicleEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<VehicleEntity> getVehicles() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<VehicleEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)Vehicle.VEHICLE }).fetch().into(VehicleEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static void putVehicle(VehicleEntity vehicle) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    Record vehicleRec = create.newRecord((Table)Vehicle.VEHICLE, vehicle);
    create.insertInto((Table)Vehicle.VEHICLE).set(vehicleRec).execute();
  }
  
  public static void updateVehicle(VehicleEntity vehicle) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    VehicleRecord vehicleRec = (VehicleRecord)create.newRecord((Table)Vehicle.VEHICLE, vehicle);
    create.executeUpdate((TableRecord)vehicleRec, Vehicle.VEHICLE.UID.equal(vehicle.getUid()));
  }
  
  public static boolean vehicleExists(String UID) {
    try {
      getVehicle(UID);
      return true;
    } catch (RecordNotFoundException ex) {
      return false;
    } 
  }
  
  public static void deleteVehicle(String UID) throws RecordNotFoundException {
    getVehicle(UID);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.delete((Table)Vehicle.VEHICLE).where(new Condition[] { Vehicle.VEHICLE.UID.equal(UID) }).execute();
  }
  
  public static VehicleTypeEntity getVehicleType(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<VehicleTypeEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)VehicleType.VEHICLE_TYPE }).where(new Condition[] { VehicleType.VEHICLE_TYPE.UID.equal(UID) }).fetch().into(VehicleTypeEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<VehicleTypeEntity> getVehicleTypes() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<VehicleTypeEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)VehicleType.VEHICLE_TYPE }).fetch().into(VehicleTypeEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static String getVehicleTypesSelect() {
    try {
      List<VehicleTypeEntity> list = getVehicleTypes();
      String options = "";
      for (VehicleTypeEntity vtype : list)
        options = options + "<option value=\"" + vtype.getUid() + "\">" + vtype.getName() + "</option>"; 
      return options;
    } catch (RecordNotFoundException e) {
      return "";
    } 
  }
  
  public static void putVehicleType(VehicleTypeEntity type) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    VehicleTypeRecord typeRec = (VehicleTypeRecord)create.newRecord((Table)VehicleType.VEHICLE_TYPE, type);
    create.insertInto((Table)VehicleType.VEHICLE_TYPE).set((Record)typeRec).execute();
  }
  
  public static void updateVehicleType(VehicleTypeEntity type) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    VehicleTypeRecord typeRec = (VehicleTypeRecord)create.newRecord((Table)VehicleType.VEHICLE_TYPE, type);
    create.executeUpdate((TableRecord)typeRec, VehicleType.VEHICLE_TYPE.UID.equal(type.getUid()));
  }
  
  public static void deleteVehicleType(String UID) throws RecordNotFoundException {
    getVehicleType(UID);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.delete((Table)VehicleType.VEHICLE_TYPE).where(new Condition[] { VehicleType.VEHICLE_TYPE.UID.equal(UID) }).execute();
  }
  
  public static LocationEntity getLocation(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<LocationEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalLocation.RENTAL_LOCATION }).where(new Condition[] { RentalLocation.RENTAL_LOCATION.UID.equal(UID) }).fetch().into(LocationEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<LocationEntity> getLocations() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<LocationEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalLocation.RENTAL_LOCATION }).fetch().into(LocationEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static String getLocationsSelect() {
    try {
      List<LocationEntity> list = getLocations();
      String options = "";
      for (LocationEntity loc : list)
        options = options + "<option value=\"" + loc.getUid() + "\">" + loc.getName() + "</option>"; 
      return options;
    } catch (RecordNotFoundException e) {
      return "";
    } 
  }
  
  public static void putLocation(LocationEntity location) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    RentalLocationRecord locRec = (RentalLocationRecord)create.newRecord((Table)RentalLocation.RENTAL_LOCATION, location);
    create.insertInto((Table)RentalLocation.RENTAL_LOCATION).set((Record)locRec).execute();
  }
  
  public static void updateLocation(LocationEntity location) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    RentalLocationRecord locRec = (RentalLocationRecord)create.newRecord((Table)RentalLocation.RENTAL_LOCATION, location);
    create.executeUpdate((TableRecord)locRec, RentalLocation.RENTAL_LOCATION.UID.equal(locRec.getUid()));
  }
  
  public static void deleteLocation(String UID) throws RecordNotFoundException {
    getLocation(UID);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.delete((Table)RentalLocation.RENTAL_LOCATION).where(new Condition[] { RentalLocation.RENTAL_LOCATION.UID.equal(UID) }).execute();
  }
  
  public static RentalTransactionEntity getRentalTransaction(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<RentalTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalTransaction.RENTAL_TRANSACTION }).where(new Condition[] { RentalTransaction.RENTAL_TRANSACTION.UID.equal(UID) }).fetch().into(RentalTransactionEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<RentalTransactionEntity> getRentalTransactions() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<RentalTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalTransaction.RENTAL_TRANSACTION }).fetch().into(RentalTransactionEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static List<RentalTransactionEntity> getRentalTransactionsForUser(String userUID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<RentalTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalTransaction.RENTAL_TRANSACTION }).where(new Condition[] { RentalTransaction.RENTAL_TRANSACTION.USER.equal(userUID) }).fetch().into(RentalTransactionEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static List<RentalTransactionEntity> getConflictingRentalTransactions(String vehicleUID, Date start_date, Date end_date) throws RecordNotFoundException {
    int istart_date = getTimestampFromDate(start_date);
    int iend_date = getTimestampFromDate(end_date);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<RentalTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)RentalTransaction.RENTAL_TRANSACTION }).where(new Condition[] { RentalTransaction.RENTAL_TRANSACTION.START_DATE.lessThan(Integer.valueOf(istart_date)).and(RentalTransaction.RENTAL_TRANSACTION.END_DATE.greaterThan(Integer.valueOf(istart_date))).and(RentalTransaction.RENTAL_TRANSACTION.STATUS.equal("ACTIVE")) }).or(RentalTransaction.RENTAL_TRANSACTION.START_DATE.lessThan(Integer.valueOf(iend_date)).and(RentalTransaction.RENTAL_TRANSACTION.END_DATE.greaterThan(Integer.valueOf(iend_date))).and(RentalTransaction.RENTAL_TRANSACTION.STATUS.equal("ACTIVE"))).or(RentalTransaction.RENTAL_TRANSACTION.START_DATE.equal(Integer.valueOf(istart_date))).or(RentalTransaction.RENTAL_TRANSACTION.START_DATE.equal(Integer.valueOf(iend_date))).or(RentalTransaction.RENTAL_TRANSACTION.END_DATE.equal(Integer.valueOf(iend_date))).or(RentalTransaction.RENTAL_TRANSACTION.END_DATE.equal(Integer.valueOf(istart_date))).fetch().into(RentalTransactionEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static void putRentalTransaction(RentalTransactionEntity transaction) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    RentalTransactionRecord tranRec = (RentalTransactionRecord)create.newRecord((Table)RentalTransaction.RENTAL_TRANSACTION, transaction);
    create.insertInto((Table)RentalTransaction.RENTAL_TRANSACTION).set((Record)tranRec).execute();
  }
  
  public static void updateRentalTransaction(RentalTransactionEntity transaction) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    RentalTransactionRecord tranRec = (RentalTransactionRecord)create.newRecord((Table)RentalTransaction.RENTAL_TRANSACTION, transaction);
    create.executeUpdate((TableRecord)tranRec, RentalTransaction.RENTAL_TRANSACTION.UID.equal(transaction.getUid()));
  }
  
  public static void deleteRentalTransaction(String UID) throws RecordNotFoundException {
    getRentalTransaction(UID);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.delete((Table)RentalTransaction.RENTAL_TRANSACTION).where(new Condition[] { RentalTransaction.RENTAL_TRANSACTION.UID.equal(UID) }).execute();
  }
  
  public static PaymentTransactionEntity getPaymentTransaction(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<PaymentTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)PaymentTransaction.PAYMENT_TRANSACTION }).where(new Condition[] { PaymentTransaction.PAYMENT_TRANSACTION.UID.equal(UID) }).fetch().into(PaymentTransactionEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<PaymentTransactionEntity> getPaymentTransactions() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<PaymentTransactionEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)PaymentTransaction.PAYMENT_TRANSACTION }).fetch().into(PaymentTransactionEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static void putPaymentTransaction(PaymentTransactionEntity transaction) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    PaymentTransactionRecord tranRec = (PaymentTransactionRecord)create.newRecord((Table)PaymentTransaction.PAYMENT_TRANSACTION, transaction);
    create.insertInto((Table)PaymentTransaction.PAYMENT_TRANSACTION).set((Record)tranRec).execute();
  }
  
  public static UserEntity getUser(String UID) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<UserEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)User.USER }).where(new Condition[] { User.USER.UID.equal(UID) }).fetch().into(UserEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static UserEntity getUserByUsername(String Username) throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<UserEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)User.USER }).where(new Condition[] { User.USER.USERNAME.equal(Username) }).fetch().into(UserEntity.class);
    if (result.size() > 0)
      return result.get(0); 
    throw new RecordNotFoundException();
  }
  
  public static List<UserEntity> getUsers() throws RecordNotFoundException {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    List<UserEntity> result = create.select(new org.jooq.Field[0]).from(new TableLike[] { (TableLike)User.USER }).fetch().into(UserEntity.class);
    if (result.size() > 0)
      return result; 
    throw new RecordNotFoundException();
  }
  
  public static void putUser(UserEntity user) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    UserRecord userRec = (UserRecord)create.newRecord((Table)User.USER, user);
    create.insertInto((Table)User.USER).set((Record)userRec).execute();
  }
  
  public static void updateUser(UserEntity user) {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    UserRecord userRec = (UserRecord)create.newRecord((Table)User.USER, user);
    create.executeUpdate((TableRecord)userRec, User.USER.UID.equal(user.getUid()));
  }
  
  public static void deleteUser(String UID) throws RecordNotFoundException {
    getUser(UID);
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.delete((Table)User.USER).where(new Condition[] { User.USER.UID.equal(UID) }).execute();
  }
  
  public static void setupDatabase(HttpServletResponse response, ServletContext context) {
    Connection conn = getConnection();
    try {
      DSLContext create = DSL.using(conn, SQLDialect.MYSQL);
      create.execute("CREATE TABLE IF NOT EXISTS USER (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), username VARCHAR(255), password VARCHAR(255), email VARCHAR(255), first_name VARCHAR(255), last_name VARCHAR(255), role VARCHAR(255), license VARCHAR(255), dateofbirth INT, street_address VARCHAR(255), state VARCHAR(255), country VARCHAR(255), zipcode INT, city VARCHAR(255), membership_expiration INT, PRIMARY KEY( id ))");
      create.execute("CREATE TABLE IF NOT EXISTS PAYMENT_TRANSACTION (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), date INT, method VARCHAR(255), description VARCHAR(255), user VARCHAR(255), reason VARCHAR(255), comments VARCHAR(255), status VARCHAR(255), PRIMARY KEY( id ))");
      create.execute("CREATE TABLE IF NOT EXISTS RENTAL_TRANSACTION (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), start_date INT, end_date INT, user VARCHAR(255), vehicle VARCHAR(255), comments VARCHAR(255), status VARCHAR(255), PRIMARY KEY( id ))");
      create.execute("CREATE TABLE IF NOT EXISTS VEHICLE (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), type VARCHAR(255), make VARCHAR(255), model VARCHAR(255), year INT, mileage INT, tag VARCHAR(255), lastservice INT, location VARCHAR(255), PRIMARY KEY( id ))");
      create.execute("CREATE TABLE IF NOT EXISTS VEHICLE_TYPE (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), name VARCHAR(255), hourly_rate FLOAT, daily_rate FLOAT, PRIMARY KEY( id ))");
      create.execute("CREATE TABLE IF NOT EXISTS RENTAL_LOCATION (id INTEGER NOT NULL AUTO_INCREMENT, uid VARCHAR(255), name VARCHAR(255), street_address VARCHAR(255), state VARCHAR(255), country VARCHAR(255), zipcode INT, city VARCHAR(255), capacity INT, PRIMARY KEY( id ))");
      UserEntity admin = new UserEntity();
      admin.setUsername("admin");
      admin.setPassword("admin");
      admin.setFirst_name("admin");
      admin.setLast_name("admin");
      admin.setStreet_Address("Broad St");
      admin.setEmail("admin@email.com");
      admin.setCity("Athens");
      admin.setCountry("USA");
      admin.setState("Georgia");
      admin.setDateofbirth(0);
      admin.setRoleFromEnum(UserType.ADMIN);
      admin.setLicense("0000-0000");
      admin.setZipcode(30000);
      putUser(admin);
      UserEntity customer = new UserEntity();
      customer.setUsername("customer");
      customer.setPassword("customer");
      customer.setFirst_name("customer");
      customer.setLast_name("customer");
      customer.setStreet_Address("Broad St");
      customer.setEmail("customer@email.com");
      customer.setCity("Athens");
      customer.setCountry("USA");
      customer.setState("Georgia");
      customer.setMembershipExpirationDate(new Date());
      customer.setDateofbirth(0);
      customer.setRoleFromEnum(UserType.CUSTOMER);
      customer.setLicense("0000-0001");
      customer.setZipcode(30000);
      putUser(customer);
      LocationEntity location1 = new LocationEntity();
      location1.setName("Downtown Athens");
      location1.setStreet_address("23 East Broad St");
      location1.setCity("Athens");
      location1.setState("GA");
      location1.setCountry("US");
      location1.setZipcode(30605);
      location1.setCapacity(35);
      putLocation(location1);
      LocationEntity location2 = new LocationEntity();
      location2.setName("Eastside Athens");
      location2.setStreet_address("278 Lexington Rd");
      location2.setCity("Athens");
      location2.setState("GA");
      location2.setCountry("US");
      location2.setZipcode(30607);
      location2.setCapacity(42);
      putLocation(location2);
      LocationEntity location3 = new LocationEntity();
      location3.setName("North Charleston");
      location3.setStreet_address("13 East Bay St");
      location3.setCity("Charleston");
      location3.setState("SC");
      location3.setCountry("US");
      location3.setZipcode(29464);
      location3.setCapacity(80);
      putLocation(location3);
      LocationEntity location4 = new LocationEntity();
      location4.setName("Downtown Nashville");
      location4.setStreet_address("45 Main St");
      location4.setCity("Nashville");
      location4.setState("TN");
      location4.setCountry("US");
      location4.setZipcode(47621);
      location4.setCapacity(53);
      putLocation(location4);
      VehicleTypeEntity regular = new VehicleTypeEntity();
      regular.setDaily_rate(Double.valueOf(110.0D));
      regular.setHourly_rate(Double.valueOf(10.0D));
      regular.setName("Regular car");
      putVehicleType(regular);
      VehicleTypeEntity luxury = new VehicleTypeEntity();
      luxury.setDaily_rate(Double.valueOf(220.0D));
      luxury.setHourly_rate(Double.valueOf(20.0D));
      luxury.setName("Luxury car");
      putVehicleType(luxury);
      VehicleTypeEntity pickup = new VehicleTypeEntity();
      pickup.setDaily_rate(Double.valueOf(185.0D));
      pickup.setHourly_rate(Double.valueOf(15.0D));
      pickup.setName("Pickup truck");
      putVehicleType(pickup);
    } catch (Exception e) {
      e.printStackTrace();
    } 
    try {
      response.sendRedirect(CarRentalServlet.getFullURL(context, "/user/home"));
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public static void destroyDatabase() {
    DSLContext create = DSL.using(getConnection(), SQLDialect.MYSQL);
    create.execute("DROP TABLE USER");
    create.execute("DROP TABLE PAYMENT_TRANSACTION");
    create.execute("DROP TABLE RENTAL_TRANSACTION");
    create.execute("DROP TABLE VEHICLE");
    create.execute("DROP TABLE VEHICLE_TYPE");
    create.execute("DROP TABLE RENTAL_LOCATION");
  }
  
  public static Date getDateFromTimestamp(int timestamp) {
    return new Date(timestamp * 1000L);
  }
  
  public static int getTimestampFromDate(Date date) {
    return (new Long(date.getTime() / 1000L)).intValue();
  }
}
