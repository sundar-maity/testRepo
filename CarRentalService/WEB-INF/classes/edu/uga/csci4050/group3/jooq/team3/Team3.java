package WEB-INF.classes.edu.uga.csci4050.group3.jooq.team3;

import edu.uga.csci4050.group3.jooq.team3.tables.PaymentTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalLocation;
import edu.uga.csci4050.group3.jooq.team3.tables.RentalTransaction;
import edu.uga.csci4050.group3.jooq.team3.tables.User;
import edu.uga.csci4050.group3.jooq.team3.tables.Vehicle;
import edu.uga.csci4050.group3.jooq.team3.tables.VehicleType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

public class Team3 extends SchemaImpl {
  private static final long serialVersionUID = -970990948L;
  
  public static final edu.uga.csci4050.group3.jooq.team3.Team3 TEAM3 = new edu.uga.csci4050.group3.jooq.team3.Team3();
  
  private Team3() {
    super(System.getenv().getOrDefault("DB_SCHEMANAME", "cloud_propeller"));
  }
  
  public final List<Table<?>> getTables() {
    List<Table<?>> result = new ArrayList();
    result.addAll(getTables0());
    return result;
  }
  
  private final List<Table<?>> getTables0() {
    return Arrays.asList((Table<?>[])new Table[] { (Table)PaymentTransaction.PAYMENT_TRANSACTION, (Table)RentalLocation.RENTAL_LOCATION, (Table)RentalTransaction.RENTAL_TRANSACTION, (Table)User.USER, (Table)Vehicle.VEHICLE, (Table)VehicleType.VEHICLE_TYPE });
  }
}
