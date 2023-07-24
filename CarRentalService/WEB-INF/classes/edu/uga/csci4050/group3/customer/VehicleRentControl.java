package WEB-INF.classes.edu.uga.csci4050.group3.customer;

import edu.uga.csci4050.group3.core.InvalidInputException;
import edu.uga.csci4050.group3.core.InvalidUrlException;
import edu.uga.csci4050.group3.core.RentalTransactionEntity;
import edu.uga.csci4050.group3.core.VehicleEntity;
import edu.uga.csci4050.group3.core.VehicleTypeEntity;
import edu.uga.csci4050.group3.db.DatabaseAbstraction;
import edu.uga.csci4050.group3.db.RecordNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.http.HttpServletRequest;

public class VehicleRentControl {
  public VehicleEntity getVehicle(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("uid")) {
      if (!request.getParameterMap().containsKey("rentalVehicleUID"))
        throw new InvalidUrlException(); 
      return DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    } 
    VehicleEntity veh = DatabaseAbstraction.getVehicle(request.getParameter("uid"));
    return veh;
  }
  
  public boolean isVehicleAvailable(HttpServletRequest request) throws RecordNotFoundException, InvalidUrlException, InvalidInputException {
    Date start_date = new Date();
    Date end_date = new Date();
    Date today_date = new Date();
    if (!request.getParameterMap().containsKey("rentalVehicleUID"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalStartDate"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalEndDate"))
      throw new InvalidUrlException(); 
    InvalidInputException invalidEx = new InvalidInputException();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    try {
      start_date = sdf.parse(request.getParameter("rentalStartDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid start date format");
    } 
    try {
      end_date = sdf.parse(request.getParameter("rentalEndDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid end date format");
    } 
    if (start_date.getTime() > end_date.getTime())
      invalidEx.addMessage("Please enter an end date that is after the start date"); 
    if (start_date.getTime() < today_date.getTime())
      invalidEx.addMessage("Start date cannot be prior to Today, please enter a future date."); 
    if (end_date.getTime() < today_date.getTime())
      invalidEx.addMessage("End date cannot be prior to Today, please enter a future date."); 
    if (invalidEx.countMessages() > 0)
      throw invalidEx; 
    VehicleEntity veh = DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    try {
      List<RentalTransactionEntity> conflicts = DatabaseAbstraction.getConflictingRentalTransactions(veh.getUid(), start_date, end_date);
      return false;
    } catch (RecordNotFoundException ex) {
      return true;
    } 
  }
  
  public List<VehicleEntity> getAlternateVehicle(HttpServletRequest request) throws RecordNotFoundException, InvalidUrlException {
    VehicleEntity unavailableVeh = DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    String thisLocation = unavailableVeh.getLocation();
    List<VehicleEntity> others = DatabaseAbstraction.getVehicles();
    Date start_date = new Date();
    Date end_date = new Date();
    if (!request.getParameterMap().containsKey("rentalVehicleUID"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalStartDate"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalEndDate"))
      throw new InvalidUrlException(); 
    InvalidInputException invalidEx = new InvalidInputException();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    try {
      start_date = sdf.parse(request.getParameter("rentalStartDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid start date format");
    } 
    try {
      end_date = sdf.parse(request.getParameter("rentalEndDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid end date format");
    } 
    List<VehicleEntity> list = DatabaseAbstraction.getVehicles();
    for (VehicleEntity veh : others) {
      if (veh.getLocation().compareToIgnoreCase(unavailableVeh.getLocation()) == 0 && 
        veh.getUid().compareToIgnoreCase(unavailableVeh.getUid()) != 0)
        list.add(veh); 
    } 
    return list;
  }
  
  public VehicleTypeEntity getType(HttpServletRequest request) throws InvalidUrlException, RecordNotFoundException {
    if (!request.getParameterMap().containsKey("rentalVehicleUID"))
      throw new InvalidUrlException(); 
    VehicleEntity vehicle = DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    return DatabaseAbstraction.getVehicleType(vehicle.getType());
  }
  
  public double getTotalAmount(HttpServletRequest request) throws RecordNotFoundException, InvalidInputException, InvalidUrlException {
    Date start_date = new Date();
    Date end_date = new Date();
    if (!request.getParameterMap().containsKey("rentalVehicleUID"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalStartDate"))
      throw new InvalidUrlException(); 
    if (!request.getParameterMap().containsKey("rentalEndDate"))
      throw new InvalidUrlException(); 
    InvalidInputException invalidEx = new InvalidInputException();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
    try {
      start_date = sdf.parse(request.getParameter("rentalStartDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid start date format");
    } 
    try {
      end_date = sdf.parse(request.getParameter("rentalEndDate"));
    } catch (ParseException e) {
      invalidEx.addMessage("Invalid end date format");
    } 
    if (invalidEx.countMessages() > 0)
      throw invalidEx; 
    VehicleEntity veh = DatabaseAbstraction.getVehicle(request.getParameter("rentalVehicleUID"));
    VehicleTypeEntity vtype = DatabaseAbstraction.getVehicleType(veh.getType());
    long diff = end_date.getTime() - start_date.getTime();
    if (diff <= 0L)
      throw new InvalidInputException(); 
    if (TimeUnit.MILLISECONDS.toHours(diff) >= 24L)
      return vtype.getDaily_rate().doubleValue() * Math.ceil(TimeUnit.MILLISECONDS.toDays(diff)); 
    return vtype.getHourly_rate().doubleValue() * TimeUnit.MILLISECONDS.toHours(diff);
  }
}
