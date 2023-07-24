package WEB-INF.classes.edu.uga.csci4050.group3.jooq.information_schema;

import org.jooq.impl.SchemaImpl;

public class InformationSchema extends SchemaImpl {
  private static final long serialVersionUID = 1961276791L;
  
  public static final edu.uga.csci4050.group3.jooq.information_schema.InformationSchema INFORMATION_SCHEMA = new edu.uga.csci4050.group3.jooq.information_schema.InformationSchema();
  
  private InformationSchema() {
    super("information_schema");
  }
}
