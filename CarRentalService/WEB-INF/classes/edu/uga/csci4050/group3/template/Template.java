package WEB-INF.classes.edu.uga.csci4050.group3.template;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

public abstract class Template {
  protected ServletContext context;
  
  public Template(ServletContext context) {
    this.context = context;
  }
  
  public abstract String render();
  
  public abstract void render(Writer paramWriter);
  
  public void render(HttpServletResponse response) {
    try {
      render(response.getWriter());
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  protected InputStreamReader locateTemplate(String name) {
    return new InputStreamReader(this.context.getResourceAsStream("/WEB-INF/templates/" + name));
  }
}
