package WEB-INF.classes.edu.uga.csci4050.group3.template;

import edu.uga.csci4050.group3.template.SelectFormType;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import edu.uga.csci4050.group3.template.Template;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletContext;

public class SelectForm extends Template {
  SelectFormType type;
  
  String preselected;
  
  String value;
  
  String name;
  
  public SelectForm(ServletContext context) {
    super(context);
    this.type = SelectFormType.COUNTRY;
    this.preselected = "";
  }
  
  public SelectForm(ServletContext context, SelectFormType type) {
    super(context);
    this.type = type;
    this.preselected = "";
  }
  
  public void setPreselectedOption(String name, String value) {
    this.preselected = name;
    this.value = value;
  }
  
  public void setName(String name) {
    this.name = name;
  }
  
  public String render() {
    StringWriter sw = new StringWriter();
    render(sw);
    return sw.toString();
  }
  
  public void render(Writer pw) {
    SimpleTemplate selectTemplate;
    if (this.type == SelectFormType.COUNTRY) {
      selectTemplate = new SimpleTemplate(this.context, "CountrySelectInput.mustache");
    } else {
      selectTemplate = new SimpleTemplate(this.context, "StateSelectInput.mustache");
    } 
    if (!this.preselected.equals("") && !this.value.equals(""))
      selectTemplate.setVariable("preselected", "<option value=\"" + this.value + "\" selected=\"selected\">" + this.preselected + "</option>"); 
    if (!this.name.equals(""))
      selectTemplate.setVariable("name", this.name); 
    selectTemplate.render(pw);
  }
}
