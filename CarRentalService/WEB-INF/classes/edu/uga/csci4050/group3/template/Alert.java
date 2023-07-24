package WEB-INF.classes.edu.uga.csci4050.group3.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import edu.uga.csci4050.group3.template.AlertType;
import edu.uga.csci4050.group3.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletContext;

public class Alert extends Template {
  String type;
  
  String servlet_name;
  
  String content;
  
  public Alert(ServletContext context, String content, AlertType type) {
    super(context);
    setType(type);
    this.servlet_name = context.getContextPath();
    this.content = content;
  }
  
  public Alert(ServletContext context, String content) {
    super(context);
    this.type = "danger";
    this.servlet_name = context.getContextPath();
    this.content = content;
  }
  
  public Alert(ServletContext context) {
    this(context, "Unknown message");
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setType(AlertType type) {
    switch (null.$SwitchMap$edu$uga$csci4050$group3$template$AlertType[type.ordinal()]) {
      case 1:
        this.type = "danger";
        break;
      case 2:
        this.type = "success";
        break;
      case 3:
        this.type = "warning";
        break;
      case 4:
        this.type = "info";
        break;
    } 
  }
  
  public void render(Writer pw) {
    DefaultMustacheFactory dmf = new DefaultMustacheFactory();
    Mustache mustache = dmf.compile(locateTemplate("Alert.mustache"), "Alert.mustache");
    mustache.execute(pw, this);
    try {
      pw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public String render() {
    StringWriter sw = new StringWriter();
    DefaultMustacheFactory dmf = new DefaultMustacheFactory();
    Mustache mustache = dmf.compile(locateTemplate("Alert.mustache"), "Alert.mustache");
    mustache.execute(sw, this);
    sw.flush();
    return sw.toString();
  }
}
