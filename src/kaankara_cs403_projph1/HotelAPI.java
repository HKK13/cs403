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
import kaankara_cs403_projph1.timestamp.VectorTimestamp;

@Path("/hotel")
public class HotelAPI {
	
	HotelManagement management = HotelManagementInstance.getInstance();
	
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
			@QueryParam("hotelData") int hotelData, @QueryParam("vt") String vt) {
		
		FlightManagement flightManagement = ManagementInstance.getManagementInstance();
		
		if (flightManagement.getPassenger(pnr) == null)
			return "No customer exists.";
		VectorTimestamp vts = new VectorTimestamp(vt);
		
		return management.reserveRoom(pnr, 1, hotelData, flightManagement.getPassenger(pnr).getPassengerName(), vts);	
		
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTicket (@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName,
			@QueryParam("vt") String vt) {
		management.deleteRoom(pnr);
		return "Room reservation canceled.\n" + management.getTimestamp(pnr); 
	}
}
