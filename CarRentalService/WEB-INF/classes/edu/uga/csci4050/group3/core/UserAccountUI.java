package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.core.AuthenticationException;
import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.CarRentalServlet;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserAccountControl;
import edu.uga.csci4050.group3.core.UserEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.db.SessionManagement;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.AlertType;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SelectForm;
import edu.uga.csci4050.group3.template.SelectFormType;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserAccountUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate accountForm = new SimpleTemplate(context, "UserAccountForm.mustache");
    lr.setTitle("Account Information");
    SessionManagement sessMan = new SessionManagement(request, response);
    UserEntity user = null;
    try {
      user = DatabaseAbstraction.getUserByUsername(sessMan.getLoggedinUsername());
    } catch (AuthenticationException e1) {
      e1.printStackTrace();
    } catch (RecordNotFoundException e) {
      e.printStackTrace();
    } 
    if (!sessMan.isUserLoggedIn()) {
      try {
        response.sendRedirect(CarRentalServlet.getFullURL(context, "/user/home"));
      } catch (IOException e) {
        e.printStackTrace();
      } 
      return;
    } 
    if (type == RequestType.GET) {
      accountForm.setVariables(user.getData());
      SelectForm countryForm = new SelectForm(context, SelectFormType.COUNTRY);
      countryForm.setPreselectedOption(user.getCountry(), user.getCountry());
      countryForm.setName("userCountry");
      SelectForm stateForm = new SelectForm(context, SelectFormType.STATE);
      stateForm.setPreselectedOption(user.getState(), user.getState());
      stateForm.setName("userState");
      accountForm.setVariable("select_country", countryForm.render());
      accountForm.setVariable("select_state", stateForm.render());
      if (user.getMembershipExpiration() == 0) {
        accountForm.setVariable("message", (new Alert(context, "You don't have a membership yet.", AlertType.WARNING)).render());
      } else if (user.getMembershipExpiration() < DatabaseAbstraction.getTimestampFromDate(new Date())) {
        accountForm.setVariable("message", (new Alert(context, "Your membership has expired.", AlertType.ERROR)).render());
      } else {
        accountForm.setVariable("message", (new Alert(context, "Your membership expires on " + user.getMembershipExpirationDate(), AlertType.INFO)).render());
      } 
      lr.setContent(accountForm.render());
      lr.render(response);
    } else {
      UserAccountControl uac = new UserAccountControl();
      try {
        uac.update(request, response);
        try {
          user = DatabaseAbstraction.getUserByUsername(sessMan.getLoggedinUsername());
        } catch (AuthenticationException e1) {
          e1.printStackTrace();
        } catch (RecordNotFoundException e) {
          e.printStackTrace();
        } 
        accountForm.setVariables(user.getData());
        SelectForm countryForm = new SelectForm(context, SelectFormType.COUNTRY);
        countryForm.setPreselectedOption(user.getCountry(), user.getCountry());
        countryForm.setName("userCountry");
        SelectForm stateForm = new SelectForm(context, SelectFormType.STATE);
        stateForm.setPreselectedOption(user.getState(), user.getState());
        stateForm.setName("userState");
        accountForm.setVariable("select_country", countryForm.render());
        accountForm.setVariable("select_state", stateForm.render());
        accountForm.setVariable("alerts", (new Alert(context, "Account updated!", AlertType.SUCCESS)).render());
        lr.setContent(accountForm.render());
        lr.render(response);
      } catch (InvalidInputException e) {
        accountForm.setVariable("alerts", e.getMessagesHtml(context));
        lr.setContent(accountForm.render());
        lr.render(response);
      } catch (Exception e) {
        e.printStackTrace();
        Alert error = new Alert(context);
        error.setContent("Something went wrong!");
        accountForm.setVariable("alerts", error.render());
        lr.setContent(accountForm.render());
        lr.render(response);
      } 
    } 
  }
}
