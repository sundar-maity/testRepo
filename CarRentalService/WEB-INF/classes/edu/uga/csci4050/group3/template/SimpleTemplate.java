package WEB-INF.classes.edu.uga.csci4050.group3.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import edu.uga.csci4050.group3.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;

public class SimpleTemplate extends Template {
  String servlet_name;
  
  String template_file;
  
  ServletContext context;
  
  Map<String, String> variables;
  
  public SimpleTemplate(ServletContext context, String templateFile) {
    super(context);
    this.servlet_name = context.getContextPath();
    this.template_file = templateFile;
    this.variables = new HashMap<>();
    this.variables.put("base_url", context.getContextPath());
    this.context = context;
  }
  
  public void setVariable(String name, String value) {
    this.variables.put(name, value);
  }
  
  public void clearVariables() {
    this.variables.clear();
  }
  
  public void setVariables(Map<String, String> variables) {
    this.variables = null;
    this.variables = variables;
    variables.put("base_url", this.context.getContextPath());
  }
  
  public void render(Writer pw) {
    DefaultMustacheFactory dmf = new DefaultMustacheFactory();
    Mustache mustache = dmf.compile(locateTemplate(this.template_file), this.template_file);
    mustache.execute(pw, this.variables);
    try {
      pw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public String render() {
    StringWriter sw = new StringWriter();
    DefaultMustacheFactory dmf = new DefaultMustacheFactory();
    Mustache mustache = dmf.compile(locateTemplate(this.template_file), this.template_file);
    mustache.execute(sw, this.variables);
    sw.flush();
    return sw.toString();
  }
}
