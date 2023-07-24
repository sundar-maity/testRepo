package WEB-INF.classes.edu.uga.csci4050.group3.jooq.test;

import org.jooq.impl.SchemaImpl;

public class Test extends SchemaImpl {
  private static final long serialVersionUID = 1100680691L;
  
  public static final edu.uga.csci4050.group3.jooq.test.Test TEST = new edu.uga.csci4050.group3.jooq.test.Test();
  
  private Test() {
    super("test");
  }
}
