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

@Path("/flight")
public class FlightAPI {
	
	FlightManagement management = ManagementInstance.getManagementInstance();
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getFlight(@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName) {
		if (pnr != 0 && passengerName != null) {
			
			Passenger passenger = management.getPassenger(pnr);
			if (passenger == null)
				return "Error: No customers.";
			Flight flight = management.getFlight(passenger.getFlightNum());
			ArrayList<Integer> ticketList = passenger.getTicketList();
		
			
			if (flight != null && passenger.getPassengerName().equals(passengerName)) {
				String message = "PassengerName: " + passengerName +
								" TicketNums:"; 
				for (int i = 0; i < ticketList.size(); i++) {
					if (i == 0)
						message += ticketList.get(i);
					else
						message += ", " + ticketList.get(i);
				}
				message += " TicketAmount:" + ticketList.size() +
					" PNR:" + passenger.getPnr() +
					" FlightNum:" + flight.getFlightNum();
				return message;
			}
			
			return "Error: No customer found.";
		}
		
		return "Error: Wrong info.";
	}
	
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String buyTicket(@QueryParam("passengerName") String passengerName, @QueryParam("flightNum") int flightNum, @QueryParam("ticketAmount") int ticketAmount) {

		String status = management.sellTicket(flightNum, ticketAmount, passengerName);
		String message = "";
		
		if (status == "OK"){
			Passenger passenger = management.getPassenger(passengerName);
			
			message += "Passenger:" + passenger.getPassengerName() +
					" Flight:" + passenger.getFlightNum() +
					" PNR:" + passenger.getPnr() +
					" Tickets:"; 
			
			ArrayList<Integer> ticketList = passenger.getTicketList();
			for (int i = 0; i < ticketList.size(); i++) {
				if (i == 0)
					message += ticketList.get(i);
				else
					message += ", " + ticketList.get(i);
			}
			message += " TicketAmount:" + passenger.getTicketList().size();
			return message;
		}
		message += "Error:" + status;
		return message;
	}
	
	@PUT
	@Produces(MediaType.TEXT_PLAIN)
	public String updateTicket (@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName, 
			@QueryParam("flightNum") String flightNum, @QueryParam("ticketAmount") String ticketAmount) {
		Passenger passenger = management.getPassenger(pnr);
		String message = "";
		
		if (passenger != null) {
			if (passengerName != null) {
				passenger.setPassengerName(passengerName);
				management.updatePassenger(pnr, passenger);
				passenger = management.getPassenger(pnr);
				message += "Passenger name changed to: " + passenger.getPassengerName() + "\n";
			}else
				message += "Passenger name not provided\n";
			
			if (flightNum != null) {
				if (management.getFlight(Integer.parseInt(flightNum)).getEmptySeats() >= passenger.getTicketList().size()) {
					ArrayList<Integer> tickets = passenger.getTicketList();
					int ticketCount = tickets.size();
					
					for (int i = 0; i < tickets.size(); i++) {
						management.cancelTicket(tickets.get(i), passenger.getFlightNum());
					}
					
					passenger.setTicketList(new ArrayList<Integer>());
					management.updatePassenger(pnr, passenger);
					management.sellTicket(Integer.parseInt(flightNum), ticketCount, passenger.getPassengerName());
					passenger = management.getPassenger(pnr);
					message += "Flight changed to: " + passenger.getFlightNum() + "\n";
				}
			}else
				message += "Flight Number is not provided\n";
			
			
			if (ticketAmount != null) {
				if (Integer.parseInt(ticketAmount) < passenger.getTicketList().size()) {
					for (int i = 0; i < passenger.getTicketList().size() - Integer.parseInt(ticketAmount); i++) {
						management.cancelTicket(passenger.getTicketList().get(passenger.getTicketList().size()-i -1), Integer.parseInt(flightNum));
					}
					message += "Ticket amount reduced to: " + passenger.getTicketList().size();
				}else if (Integer.parseInt(ticketAmount) > passenger.getTicketList().size()) {
					management.sellTicket(passenger.getFlightNum(), Integer.parseInt(ticketAmount) - passenger.getTicketList().size(), passenger.getPassengerName());
					message += "Ticket amount increased to " + passenger.getTicketList().size() + Integer.parseInt(ticketAmount) + "\n";
				}else {
					message += "No change in ticket amount";
				}
			}else
				message += "Ticket amount not provided.";
		}else
			message += "Customer does not exist.";
		
		
		return message;
	}
	
	@DELETE
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteTicket (@QueryParam("pnr") int pnr, @QueryParam("passengerName") String passengerName) {
		Passenger passenger = management.getPassenger(pnr);
		for (int i = 0; i < passenger.getTicketList().size(); i++) {
			management.cancelTicket(passenger.getTicketList().get(i), passenger.getFlightNum());
		}
		management.deletePassenger(pnr);
		return "Ticket(s)canceled.";
	}
}
