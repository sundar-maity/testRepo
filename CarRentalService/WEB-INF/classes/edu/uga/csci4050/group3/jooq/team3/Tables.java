package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3;

import edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.User;
import edu.uga.csci4050.group3.jooq.team3.tables.Vehicle;
import edu.uga.csci4050.group3.jooq.team3.tables.VehicleType;

public class Tables {
  public static final PaymentTransaction PAYMENT_TRANSACTION = PaymentTransaction.PAYMENT_TRANSACTION;
  
  public static final RentalLocation RENTAL_LOCATION = RentalLocation.RENTAL_LOCATION;
  
  public static final RentalTransaction RENTAL_TRANSACTION = RentalTransaction.RENTAL_TRANSACTION;
  
  public static final User USER = User.USER;
  
  public static final Vehicle VEHICLE = Vehicle.VEHICLE;
  
  public static final VehicleType VEHICLE_TYPE = VehicleType.VEHICLE_TYPE;
}
