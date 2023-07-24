package WEB-INF.classes.edu.uga.csci4050.group3.template;

import edu.uga.csci4050.group3.template.SimpleTemplate;
import edu.uga.csci4050.group3.template.Template;
import java.io.Writer;
import javax.servlet.ServletContext;

public class ConfirmationDialog extends Template {
  private SimpleTemplate internalTemplate;
  
  private String description;
  
  private String url_yes;
  
  private String url_no;
  
  public ConfirmationDialog(ServletContext context, String description, String url_yes, String url_no) {
    super(context);
    this.description = description;
    this.url_yes = url_yes;
    this.url_no = url_no;
    this.internalTemplate = new SimpleTemplate(context, "ConfirmationDialog.mustache");
  }
  
  public String render() {
    this.internalTemplate.setVariable("description", this.description);
    this.internalTemplate.setVariable("url_yes", this.url_yes);
    this.internalTemplate.setVariable("url_no", this.url_no);
    return this.internalTemplate.render();
  }
  
  public void render(Writer pw) {
    this.internalTemplate.setVariable("description", this.description);
    this.internalTemplate.setVariable("url_yes", this.url_yes);
    this.internalTemplate.setVariable("url_no", this.url_no);
    this.internalTemplate.render(pw);
  }
}
