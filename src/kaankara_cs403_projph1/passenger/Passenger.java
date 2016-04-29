package kaankara_cs403_projph1.passenger;

import java.util.ArrayList;

public class Passenger {
	private ArrayList<Integer> ticketList;
	private String passengerName;
	private int pnr, flightNum;
	
	public Passenger(String passengerName, int pnr, int flightNum) {
		this.ticketList = new ArrayList<>();
		this.passengerName = passengerName;
		this.pnr = pnr;
		this.flightNum = flightNum;
	}
	
	public int getFlightNum() {
		return flightNum;
	}
	
	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	public void addTicket(int ticketNum) {
		ticketList.add(ticketNum);
	}
	
	public ArrayList<Integer> getTicketList() {
		return ticketList;
	}

	public void setTicketList (ArrayList<Integer> ticketList) {
		this.ticketList = ticketList;
	}
	
	public String getPassengerName() {
		return passengerName;
	}
	
	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
	
	public void setPnr(int pnr) {
		this.pnr = pnr;
	}
	
	public int getPnr() {
		return pnr;
	}
}
