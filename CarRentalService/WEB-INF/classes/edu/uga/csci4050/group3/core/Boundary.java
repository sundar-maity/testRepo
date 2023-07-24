package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.RequestType;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Boundary {
  void handleRequest(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, ServletContext paramServletContext, RequestType paramRequestType);
}
