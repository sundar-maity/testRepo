package WEB-INF.classes.edu.uga.csci4050.group3.jooq.rentalservice;

import org.jooq.impl.SchemaImpl;

public class Rentalservice extends SchemaImpl {
  private static final long serialVersionUID = -443856081L;
  
  public static final edu.uga.csci4050.group3.jooq.rentalservice.Rentalservice RENTALSERVICE = new edu.uga.csci4050.group3.jooq.rentalservice.Rentalservice();
  
  private Rentalservice() {
    super("rentalservice");
  }
}
