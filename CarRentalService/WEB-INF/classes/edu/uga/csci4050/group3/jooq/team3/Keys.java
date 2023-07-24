package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3;

import edu.uga.csci4050.group3.jooq.team3.tables.records.PaymentTransactionRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalLocationRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.RentalTransactionRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.UserRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleRecord;
import edu.uga.csci4050.group3.jooq.team3.tables.records.VehicleTypeRecord;
import org.jooq.Identity;
import org.jooq.UniqueKey;

public class Keys {
  public static final Identity<PaymentTransactionRecord, Integer> IDENTITY_PAYMENT_TRANSACTION = Identities0.IDENTITY_PAYMENT_TRANSACTION;
  
  public static final Identity<RentalLocationRecord, Integer> IDENTITY_RENTAL_LOCATION = Identities0.IDENTITY_RENTAL_LOCATION;
  
  public static final Identity<RentalTransactionRecord, Integer> IDENTITY_RENTAL_TRANSACTION = Identities0.IDENTITY_RENTAL_TRANSACTION;
  
  public static final Identity<UserRecord, Integer> IDENTITY_USER = Identities0.IDENTITY_USER;
  
  public static final Identity<VehicleRecord, Integer> IDENTITY_VEHICLE = Identities0.IDENTITY_VEHICLE;
  
  public static final Identity<VehicleTypeRecord, Integer> IDENTITY_VEHICLE_TYPE = Identities0.IDENTITY_VEHICLE_TYPE;
  
  public static final UniqueKey<PaymentTransactionRecord> KEY_PAYMENT_TRANSACTION_PRIMARY = UniqueKeys0.KEY_PAYMENT_TRANSACTION_PRIMARY;
  
  public static final UniqueKey<RentalLocationRecord> KEY_RENTAL_LOCATION_PRIMARY = UniqueKeys0.KEY_RENTAL_LOCATION_PRIMARY;
  
  public static final UniqueKey<RentalTransactionRecord> KEY_RENTAL_TRANSACTION_PRIMARY = UniqueKeys0.KEY_RENTAL_TRANSACTION_PRIMARY;
  
  public static final UniqueKey<UserRecord> KEY_USER_PRIMARY = UniqueKeys0.KEY_USER_PRIMARY;
  
  public static final UniqueKey<VehicleRecord> KEY_VEHICLE_PRIMARY = UniqueKeys0.KEY_VEHICLE_PRIMARY;
  
  public static final UniqueKey<VehicleTypeRecord> KEY_VEHICLE_TYPE_PRIMARY = UniqueKeys0.KEY_VEHICLE_TYPE_PRIMARY;
}
