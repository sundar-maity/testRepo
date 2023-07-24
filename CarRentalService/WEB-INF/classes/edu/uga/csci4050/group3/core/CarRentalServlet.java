package WEB-INF.classes.edu.uga.csci4050.group3.core;

import edu.uga.csci4050.group3.admin.LocationCreateUI;
import edu.uga.csci4050.group3.admin.LocationDeleteUI;
import edu.uga.csci4050.group3.admin.LocationUpdateUI;
import edu.uga.csci4050.group3.admin.PaymentListUI;
import edu.uga.csci4050.group3.admin.RentalCancelUI;
import edu.uga.csci4050.group3.admin.RentalListUI;
import edu.uga.csci4050.group3.admin.ServiceManagementUI;
import edu.uga.csci4050.group3.admin.UserListUI;
import edu.uga.csci4050.group3.admin.UserUpdateRoleUI;
import edu.uga.csci4050.group3.admin.VehicleCreateUI;
import edu.uga.csci4050.group3.admin.VehicleDeleteUI;
import edu.uga.csci4050.group3.admin.VehicleTypeCreateUI;
import edu.uga.csci4050.group3.admin.VehicleTypeDeleteUI;
import edu.uga.csci4050.group3.admin.VehicleTypeListUI;
import edu.uga.csci4050.group3.admin.VehicleTypeUpdateUI;
import edu.uga.csci4050.group3.admin.VehicleUpdateUI;
import edu.uga.csci4050.group3.core.HomeUI;
import edu.uga.csci4050.group3.core.LocationListUI;
import edu.uga.csci4050.group3.core.LocationViewUI;
import edu.uga.csci4050.group3.core.MembershipCancelUI;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.UserAccountUI;
import edu.uga.csci4050.group3.core.UserCancelUI;
import edu.uga.csci4050.group3.core.UserHomeUI;
import edu.uga.csci4050.group3.core.UserLoginUI;
import edu.uga.csci4050.group3.core.UserLogoutUI;
import edu.uga.csci4050.group3.core.UserRegisterUI;
import edu.uga.csci4050.group3.core.VehicleListUI;
import edu.uga.csci4050.group3.customer.LocationFilterUI;
import edu.uga.csci4050.group3.customer.MembershipPaymentUI;
import edu.uga.csci4050.group3.customer.MembershipUI;
import edu.uga.csci4050.group3.customer.ReservationCancelUI;
import edu.uga.csci4050.group3.customer.ReservationListUI;
import edu.uga.csci4050.group3.customer.ReservationReturnUI;
import edu.uga.csci4050.group3.customer.VehicleRentConfirmUI;
import edu.uga.csci4050.group3.customer.VehicleRentUI;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.template.LayoutRoot;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CarRentalServlet extends HttpServlet {
  private static final long serialVersionUID = 1L;
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doRequest(request, response, RequestType.GET);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    doRequest(request, response, RequestType.POST);
  }
  
  private void doRequest(HttpServletRequest request, HttpServletResponse response, RequestType type) {
    ServletContext context = getServletContext();
    if (uriMatches(request, "/login")) {
      (new UserLoginUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/") || uriMatches(request, "/home")) {
      (new HomeUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicle/create")) {
      (new VehicleCreateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicle/delete")) {
      (new VehicleDeleteUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicle/update")) {
      (new VehicleUpdateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicles")) {
      (new VehicleListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicletypes")) {
      (new VehicleTypeListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicletype/create")) {
      (new VehicleTypeCreateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicletype/update")) {
      (new VehicleTypeUpdateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/vehicletype/delete")) {
      (new VehicleTypeDeleteUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/locations")) {
      (new LocationListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/locations/filter")) {
      (new LocationFilterUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/location")) {
      (new LocationViewUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/location/create")) {
      (new LocationCreateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/location/update")) {
      (new LocationUpdateUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/location/delete")) {
      (new LocationDeleteUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/rent")) {
      (new VehicleRentUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/rentals")) {
      (new RentalListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/rental/cancel")) {
      (new RentalCancelUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/rental/confirm")) {
      (new VehicleRentConfirmUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/reservations")) {
      (new ReservationListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/reservation/return")) {
      (new ReservationReturnUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/reservation/cancel")) {
      (new ReservationCancelUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/users")) {
      (new UserListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/register")) {
      (new UserRegisterUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/home")) {
      (new UserHomeUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/account")) {
      (new UserAccountUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/delete")) {
      (new UserCancelUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/login")) {
      (new UserLoginUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/logout")) {
      (new UserLogoutUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/user/updaterole")) {
      (new UserUpdateRoleUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/service")) {
      (new ServiceManagementUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/payments")) {
      (new PaymentListUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/database/setup")) {
      DatabaseAbstraction.setupDatabase(response, context);
    } else if (uriMatches(request, "/database/destroy")) {
      DatabaseAbstraction.destroyDatabase();
    } else if (uriMatches(request, "/membership")) {
      (new MembershipUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/membership/purchase")) {
      (new MembershipPaymentUI()).handleRequest(request, response, context, type);
    } else if (uriMatches(request, "/membership/cancel")) {
      (new MembershipCancelUI()).handleRequest(request, response, context, type);
    } else {
      LayoutRoot lr = new LayoutRoot(getServletContext(), request, response);
      lr.setContent("Page:" + request.getRequestURI());
      lr.render(response);
    } 
  }
  
  public static String getFullURL(ServletContext context, String URL) {
    return context.getContextPath() + URL;
  }
  
  public static void redirect(ServletContext context, HttpServletResponse response, String URL) {
    try {
      response.sendRedirect(getFullURL(context, URL));
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  private boolean uriMatches(HttpServletRequest request, String uri) {
    return request.getRequestURI().equals(request.getContextPath() + uri);
  }
}
