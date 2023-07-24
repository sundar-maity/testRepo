package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.Boundary;
import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RequestType;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.customer.VehicleRentControl;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import edu.uga.csci4050.group3.template.Alert;
import edu.uga.csci4050.group3.template.LayoutRoot;
import edu.uga.csci4050.group3.template.SimpleTemplate;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class VehicleRentUI implements Boundary {
  public void handleRequest(HttpServletRequest request, HttpServletResponse response, ServletContext context, RequestType type) {
    LayoutRoot lr = new LayoutRoot(context, request, response);
    SimpleTemplate cardTemplate = new SimpleTemplate(context, "VehicleCardCustomer.mustache");
    SimpleTemplate vehicleRentLayout = new SimpleTemplate(context, "VehicleRent.mustache");
    VehicleRentControl control = new VehicleRentControl();
    lr.setTitle("Rental");
    if (type == RequestType.GET)
      try {
        VehicleEntity vehicle = control.getVehicle(request);
        cardTemplate.setVariables(vehicle.getCustomerData());
        vehicleRentLayout.setVariable("uid", vehicle.getUid());
        vehicleRentLayout.setVariable("vehicle_card", cardTemplate.render());
        lr.setContent(vehicleRentLayout.render());
        lr.render(response);
        return;
      } catch (InvalidUrlException e) {
        lr.setContent((new Alert(context, "Invalid URL format")).render());
        lr.render(response);
        return;
      } catch (RecordNotFoundException e) {
        lr.setContent((new Alert(context, "Vehicle with UID not found")).render());
        lr.render(response);
        return;
      }  
    try {
      if (control.isVehicleAvailable(request)) {
        SimpleTemplate availableLayout = new SimpleTemplate(context, "VehicleRentAvailable.mustache");
        availableLayout.setVariable("uid", request.getParameter("rentalVehicleUID"));
        availableLayout.setVariable("start_date", request.getParameter("rentalStartDate"));
        availableLayout.setVariable("end_date", request.getParameter("rentalEndDate"));
        VehicleTypeEntity vtype = control.getType(request);
        availableLayout.setVariable("daily_rate", String.valueOf(vtype.getDaily_rate()));
        availableLayout.setVariable("hourly_rate", String.valueOf(vtype.getHourly_rate()));
        availableLayout.setVariable("amount", String.valueOf(control.getTotalAmount(request)));
        VehicleEntity vehicle = control.getVehicle(request);
        cardTemplate.setVariables(vehicle.getCustomerData());
        availableLayout.setVariable("vehicle_card", cardTemplate.render());
        lr.setContent(availableLayout.render());
        lr.render(response);
      } else {
        SimpleTemplate unavailableLayout = new SimpleTemplate(context, "VehicleRentUnavailable.mustache");
        VehicleEntity vehicle = control.getVehicle(request);
        cardTemplate.setVariables(vehicle.getCustomerData());
        unavailableLayout.setVariable("vehicle_card", cardTemplate.render());
        List<VehicleEntity> alternateVeh = control.getAlternateVehicle(request);
        SimpleTemplate cardTemplate2 = new SimpleTemplate(context, "VehicleCardCustomer.mustache");
        String vehicles = "";
        if (alternateVeh.size() == 0) {
          unavailableLayout.setVariable("alternate_vehicles", "No other available cars at this location during that rental period");
        } else {
          for (VehicleEntity veh : alternateVeh) {
            if (veh.getLocation().compareToIgnoreCase(vehicle.getLocation()) == 0) {
              SimpleTemplate anotherCard = new SimpleTemplate(context, "VehicleCard.mustache");
              anotherCard.setVariables(veh.getCustomerData());
              vehicles = vehicles + anotherCard.render();
            } 
          } 
          unavailableLayout.setVariable("alternate_vehicles", vehicles);
        } 
        lr.setContent(unavailableLayout.render());
        lr.render(response);
      } 
    } catch (RecordNotFoundException e) {
      lr.setContent((new Alert(context, "Vehicle with UID not found")).render());
      lr.render(response);
      return;
    } catch (InvalidUrlException e) {
      lr.setContent((new Alert(context, "Invalid URL format")).render());
      lr.render(response);
      return;
    } catch (InvalidInputException e) {
      lr.setContent(e.getMessagesHtml(context));
      lr.render(response);
      return;
    } 
  }
}
