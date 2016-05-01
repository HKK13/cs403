package kaankara_cs403_projph1;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import kaankara_cs403_projph1.flight.Flight;
import kaankara_cs403_projph1.flight.FlightManagement;
import kaankara_cs403_projph1.flight.ManagementInstance;
import kaankara_cs403_projph1.hotel.HotelManagement;
import kaankara_cs403_projph1.hotel.HotelManagementInstance;
import kaankara_cs403_projph1.passenger.Passenger;
import kaankara_cs403_projph1.rental.RentalInstance;
import kaankara_cs403_projph1.rental.RentalManagement;
import kaankara_cs403_projph1.timestamp.VectorTimestamp;

@Path("/rental")
public class RentalAPI {
	
	RentalManagement management = RentalInstance.getInstance();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFlight(@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName,
			@QueryParam("vt") String vt) {
		String response = management.getReservation(pnr);
		if (response != null)
			return response;
		return "Wrong Info.";
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTicket (@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName, 
			@QueryParam("carId") int carId, @QueryParam("vt") String vt) {
		
		FlightManagement flightManagement = ManagementInstance.getManagementInstance();
		
		if (pnr < 0 || passengerName == null || carId < 0 || vt == null)
			return "Faulty info.";
		
		if (flightManagement.getPassenger(pnr) == null)
			return "No customer exists. Cannot make any car rent";
		VectorTimestamp vts = new VectorTimestamp(vt);
		
		
		String response = management.reserveCar(pnr, carId, flightManagement.getPassenger(pnr).getPassengerName(), vts);	
		if (response == null)
			return "Cannot complete the reservation";
		
		return response;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTicket (@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName,
			@QueryParam("vt") String vt) {
		management.deleteCar(pnr);
		return "Car reservation canceled.\n" + management.getTimestamp(pnr); 
	}
}
