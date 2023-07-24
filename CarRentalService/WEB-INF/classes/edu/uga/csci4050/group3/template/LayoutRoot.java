package WEB-INF.classes.edu.uga.csci4050.group3.template;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import edu.uga.csci4050.group3.template.Template;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LayoutRoot extends Template {
  String title;
  
  String servlet_name;
  
  String content;
  
  String username;
  
  String side_menu;
  
  HttpServletRequest request;
  
  HttpServletResponse response;
  
  public LayoutRoot(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
    super(context);
    this.title = "Untitled Page";
    this.servlet_name = context.getContextPath();
    this.request = request;
    this.response = response;
  }
  
  public void setContent(String content) {
    this.content = content;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void render(Writer pw) {
    SessionManagement sessMan = new SessionManagement(this.request, this.response);
    try {
      this.username = sessMan.getLoggedinUsername();
    } catch (AuthenticationException authenticationException) {}
    if (this.username == null) {
      SimpleTemplate compactLogin = new SimpleTemplate(this.context, "CompactLoginForm.mustache");
      this.side_menu = compactLogin.render();
    } else {
      SimpleTemplate badge = new SimpleTemplate(this.context, "UserBadge.mustache");
      badge.setVariable("username", this.username);
      this.side_menu = badge.render();
    } 
    DefaultMustacheFactory dmf = new DefaultMustacheFactory();
    Mustache mustache = dmf.compile(locateTemplate("LayoutRoot.mustache"), "LayoutRoot.mustache");
    mustache.execute(pw, this);
    try {
      pw.flush();
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public String render() {
    StringWriter sw = new StringWriter();
    render(sw);
    return sw.toString();
  }
}
