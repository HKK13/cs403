package kaankara_cs403_projph1.flight;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

import kaankara_cs403_projph1.passenger.Passenger;

public class FlightManagement {
	private static ArrayList<Flight> flightList ;
	private static ArrayList<Passenger> passengerList;
	private static ArrayList<Integer> pnrList;
	
	public FlightManagement() {
		FlightManagement.pnrList = new ArrayList<>();
		FlightManagement.flightList = new ArrayList<>();
		FlightManagement.passengerList = new ArrayList<>();
		for (int i = 100; i < 110; i++) {
			flightList.add(new Flight(i));
		}
	}
	
	public Flight getFlight(int flightNum) {
	
		for (int i = 0; i < 10; i++) {
			if (flightList.get(i).getFlightNum() == flightNum) {
				return flightList.get(i);
			}
		}
		return null;
	}
	
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
					
					return "OK";
					
				}else {
					return "Not enough empty seats.";
				}
			}
		}
		return "No Flight Exist.";
	}
	
	public ArrayList getFlightList() {
		return this.flightList;
	}
	
	public synchronized int generateTicket(Flight flight) {
		int ticketNum = -1;
		while(!flight.getTicketList().contains(ticketNum = new Random().nextInt(50) + 1)) {
			return ticketNum;
		};
		return ticketNum;
	}
	
	public synchronized boolean cancelTicket(int ticketNum, int flightNum) {
		for (int i = 0; i < 10; i++) {
			if (flightList.get(i).getFlightNum() == flightNum) {
				flightList.get(i).cancelTicket(ticketNum);
				flightList.get(i).setEmptySeats(flightList.get(i).getEmptySeats() + 1);
				return true;
			}
		}
		
		return false;
	}
	
	public ArrayList<Integer> findTickets (String passengerName) {
		
		for (int i = 0; i < 10; i++) {
			if (passengerName == passengerList.get(i).getPassengerName())
				return  passengerList.get(i).getTicketList();
		}
		
		return null;
	}
	
	public Passenger getPassenger(int pnr) {
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPnr() == pnr)
				return passengerList.get(i);
		}
		
		return null;
	}
	
	public Passenger getPassenger(String passengerName) {
		for (int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPassengerName() == passengerName)
				return passengerList.get(i);
		}
		
		return null;
	}
	
	public void updatePassenger (int pnr, Passenger passenger) {
		for(int i = 0; i < passengerList.size(); i++) {
			if (passengerList.get(i).getPnr() == pnr) {
				passengerList.set(i, passenger);
			}
		}
	}
	
	public void deletePassenger (int pnr) {
		passengerList.remove(getPassenger(pnr));
	}
}
