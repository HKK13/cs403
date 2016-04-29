package kaankara_cs403_projph1.flight;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import kaankara_cs403_projph1.passenger.Passenger;
import kaankara_cs403_projph1.timestamp.VectorTimestamp;

public class FlightManagement {
	private static ArrayList<Flight> flightList ;
	private static ArrayList<Passenger> passengerList;
	private static ArrayList<Integer> pnrList;
	private static VectorTimestamp timestamp;
	
	/**
	 * Construct a management object to manage all flight related
	 * functions.
	 */
	public FlightManagement() {
		FlightManagement.pnrList = new ArrayList<>();
		FlightManagement.flightList = new ArrayList<>();
		FlightManagement.passengerList = new ArrayList<>();
		timestamp = new VectorTimestamp(3);
		for (int i = 100; i < 110; i++) {
			flightList.add(new Flight(i));
		}
	}
	
	/**
	 * Get flight corresponding to the flight number provided.
	 * @param flightNum
	 * @return
	 */
	public Flight getFlight(int flightNum) {
		
		for (int i = 0; i < 10; i++) {
			if (flightList.get(i).getFlightNum() == flightNum) {
				return flightList.get(i);
			}
		}
		return null;
	}
	
	/**
	 * Sells ticket to the customer with the information provided.
	 * @param flightNum
	 * @param ticketNum
	 * @param passengerName
	 * @return
	 */
	public synchronized String sellTicket (int flightNum, int ticketNum, String passengerName) {
		for (int i = 0; i < 10; i++) {
			if (flightList.get(i).getFlightNum() == flightNum) {
				if(flightList.get(i).getEmptySeats() >= ticketNum) {					
					flightList.get(i).setEmptySeats((flightList.get(i).getEmptySeats() - ticketNum));
					int ticketPrice = ((ticketNum) * (50 - flightList.get(i).getEmptySeats() + 1) * 10);
					flightList.get(i).setTotalRevenue((flightList.get(i).getTotalRevenue() + ticketPrice));
					String tickets = "";

					//TODO CHECKS
					
					Passenger newPassenger = new Passenger(passengerName, Integer.parseInt(new BigInteger(31, new Random()).toString()), flightNum);
					passengerList.add(newPassenger);
					
					for (int index = 0; index < ticketNum; index++) {
						tickets += ",";
						int newTicket = generateTicket(flightList.get(i));
						passengerList.get(passengerList.size()-1).addTicket(newTicket);
						flightList.get(i).addTicket(newTicket);
						tickets += newTicket;
					}
					
					timestamp.increaseTimeOf(0);
					return "OK";
					
				}else {
					return "Not enough empty seats.";
				}
			}
		}
		return "No Flight Exist.";
	}
	
	/**
	 * Returns all of the existing flights.
	 * @return
	 */
	public ArrayList getFlightList() {
		return this.flightList;
	}
	
	/**
	 * Generate ticket number for a single customer.
	 * @param flight
	 * @return
	 */
	public synchronized int generateTicket(Flight flight) {
		int ticketNum = -1;
		while(!flight.getTicketList().contains(ticketNum = new Random().nextInt(50) + 1)) {
			return ticketNum;
		};
		return ticketNum;
	}
	
	/**
	 * Cancels a single ticket corresponding to the ticket number of 
	 * a flight that are provided.
	 * @param ticketNum
	 * @param flightNum
	 * @return
	 */
	public synchronized boolean cancelTicket(int ticketNum, int flightNum) {
		for (int i = 0; i < 10; i++) {
			if (flightList.get(i).getFlightNum() == flightNum) {
				flightList.get(i).cancelTicket(ticketNum);
				flightList.get(i).setEmptySeats(flightList.get(i).getEmptySeats() + 1);
				timestamp.increaseTimeOf(0);
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Returns the tickets of the passenger.
	 * @param passengerName
	 * @return
	 */
	public ArrayList<Integer> findTickets (String passengerName) {
		
		for (int i = 0; i < 10; i++) {
			if (passengerName == passengerList.get(i).getPassengerName())
				return  passengerList.get(i).getTicketList();
		}
		
		return null;
	}
	
	/**
	 * Gets the passenger object by its unique pnr number.
	 * @param pnr
	 * @return
	 */
	public Passenger getPassenger(int pnr) {
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPnr() == pnr)
				return passengerList.get(i);
		}
		
		return null;
	}
	
	/**
	 * Returns customer finding it by name
	 * This method is not guaranteed to find the exact customer.
	 * @param passengerName
	 * @return
	 */
	public Passenger getPassenger(String passengerName) {
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPassengerName() == passengerName)
				return passengerList.get(i);
		}
		
		return null;
	}
	
	/**
	 * Updates the passenger with the provided pnr number
	 * with the provided passenger object. 
	 * @param pnr
	 * @param passenger
	 */
	public void updatePassenger (int pnr, Passenger passenger) {
		for(int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPnr() == pnr) {
				passengerList.set(i, passenger);
				timestamp.increaseTimeOf(0);
			}
		}
	}
	
	/**
	 * Deletes passenger.
	 * @param pnr
	 */
	public void deletePassenger (int pnr) {
		passengerList.remove(getPassenger(pnr));
		timestamp.increaseTimeOf(0);
	}
	
	/**
	 * Checks timestamp for the management methods.
	 * @param vt
	 * @return boolean
	 */
	public boolean checkTimestamp(int[] vt) {
		//TODO If is not equal get data from other servers.
		return timestamp.equals(new VectorTimestamp(vt));
	}
}
