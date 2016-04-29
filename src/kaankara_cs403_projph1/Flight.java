package kaankara_cs403_projph1;
import java.io.Serializable;
import java.util.ArrayList;

public class Flight implements Serializable{

	private int flightNum, emptySeats, totalRevenue;
	private ArrayList ticketList;
	
	public Flight(int flightNum) {
		this.flightNum = flightNum;
		this.emptySeats = 189;
		this.totalRevenue = 0;
		this.ticketList = new ArrayList<>();
	}

	public ArrayList getTicketList() {
		return ticketList;
	}

	public void addTicketList(int ticketNum) {
		if (!ticketList.contains(ticketNum)) {
			ticketList.add(ticketNum);
		}
	}

	public int getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(int flightNum) {
		this.flightNum = flightNum;
	}

	public int getEmptySeats() {
		return emptySeats;
	}

	public void setEmptySeats(int emptySeats) {
		this.emptySeats = emptySeats;
	}

	public int getTotalRevenue() {
		return totalRevenue;
	}

	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	
	public void addTicket(int ticketNum) {
		ticketList.add(ticketNum);
	}
	
	public synchronized void cancelTicket(int ticketNun) {
		ticketList.remove(ticketList.indexOf(ticketNun));
	}
}
