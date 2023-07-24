package WEB-INF.classes.edu.uga.csci4050.group3.db;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.core.UserType;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionException;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionManagement {
  public static final String COOKIE_NAME = "rentalAuth";
  
  public static final String SESSION_NAME = "rentalAuth";
  
  private HttpServletRequest request;
  
  private HttpServletResponse response;
  
  public SessionManagement(HttpServletRequest request, HttpServletResponse response) {
    this.request = request;
    this.response = response;
  }
  
  public String getLoggedinUsername() throws AuthenticationException {
    if (this.request.getSession().getAttribute("rentalAuth") == null) {
      Date d = new Date(123123132L);
      System.out.println("Current time-zone offset is : " + d.getTimezoneOffset());
      System.out.println("time" + d.getMinutes());
      throw new AuthenticationException();
    } 
    return (String)this.request.getSession().getAttribute("rentalAuth");
  }
  
  public void updateLoggedinUsername(String username) throws AuthenticationException {
    if (this.request.getSession().getAttribute("rentalAuth") == null)
      throw new AuthenticationException(); 
    this.request.getSession().setAttribute("rentalAuth", username);
  }
  
  public boolean isUserLoggedIn() {
    try {
      getLoggedinUsername();
      return true;
    } catch (AuthenticationException e) {
      return false;
    } 
  }
  
  public boolean requireRole(List<UserType> roles, String redirectUrl) {
    if (!isUserLoggedIn()) {
      try {
        this.response.sendRedirect(redirectUrl);
      } catch (IOException e) {
        e.printStackTrace();
      } 
      return true;
    } 
    try {
      String username = getLoggedinUsername();
      UserEntity user = DatabaseAbstraction.getUserByUsername(username);
      boolean roleMatch = false;
      UserType role = user.getRoleEnum();
      for (UserType type : roles) {
        if (type.equals(role))
          roleMatch = true; 
      } 
      if (roleMatch)
        return false; 
      try {
        this.response.sendRedirect(redirectUrl);
      } catch (IOException ex) {
        ex.printStackTrace();
      } 
    } catch (AuthenticationException e) {
      e.printStackTrace();
      try {
        this.response.sendRedirect(redirectUrl);
      } catch (IOException ex) {
        ex.printStackTrace();
      } 
      return true;
    } catch (RecordNotFoundException e) {
      try {
        this.response.sendRedirect(redirectUrl);
      } catch (IOException ex) {
        ex.printStackTrace();
      } 
      return true;
    } 
    return false;
  }
  
  public boolean requireRole(UserType role, String redirectUrl) {
    List<UserType> roles = new ArrayList<>();
    roles.add(role);
    return requireRole(roles, redirectUrl);
  }
  
  public void createSession(String username) throws SessionException {
    if (isUserLoggedIn())
      throw new SessionException(); 
    HttpSession session = this.request.getSession(true);
    session.setAttribute("rentalAuth", username);
    Cookie cookie = new Cookie("rentalAuth", username);
    cookie.setMaxAge(3600);
    session.setMaxInactiveInterval(3600);
    this.response.addCookie(cookie);
  }
  
  public void invalidateSession() {
    if (!isUserLoggedIn())
      return; 
    if (this.request.getSession() != null)
      this.request.getSession().invalidate(); 
  }
  
  public UserType getUserRole() throws SessionException {
    if (!isUserLoggedIn())
      throw new SessionException(); 
    try {
      UserEntity user = DatabaseAbstraction.getUserByUsername(getLoggedinUsername());
      return user.getRoleEnum();
    } catch (RecordNotFoundException e) {
      throw new SessionException();
    } catch (AuthenticationException e) {
      throw new SessionException();
    } 
  }
}
