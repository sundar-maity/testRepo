package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.AlertType;
import java.util.ArrayList;
import javax.servlet.ServletContext;

public class InvalidInputException extends Exception {
  private static final long serialVersionUID = 1L;
  
  private ArrayList<String> messages;
  
  public InvalidInputException() {
    this.messages = new ArrayList<>();
  }
  
  public InvalidInputException(String message) {
    this.messages = new ArrayList<>();
    this.messages.add(message);
  }
  
  public void addMessage(String message) {
    this.messages.add(message);
  }
  
  public ArrayList<String> getMessages() {
    return this.messages;
  }
  
  public int countMessages() {
    return this.messages.size();
  }
  
  public String getMessagesHtml(ServletContext context) {
    String html = "";
    for (String message : this.messages) {
      Alert alert = new Alert(context);
      alert.setType(AlertType.ERROR);
      alert.setContent(message);
      html = html + alert.render();
    } 
    return html;
  }
}
