package kaankara_cs403_projph1.hotel;

import java.util.ArrayList;

import kaankara_cs403_projph1.passenger.Passenger;
import kaankara_cs403_projph1.passenger.PassengerTimestamp;
import kaankara_cs403_projph1.timestamp.VectorTimestamp;

public class HotelManagement {
	private ArrayList<HotelReservation> customerList;
	private ArrayList<Hotel> hotelList;
	private ArrayList<PassengerTimestamp> timestamps;
	
	public HotelManagement() {
		customerList = new ArrayList<>();
		hotelList = new ArrayList<>();
		timestamps = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			hotelList.add(new Hotel(i));
		}
	}
	
	public String getReservation(int pnr) {
		for(int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getPnr() == pnr) {
				return "Customer Name: " + customerList.get(i).getPassengerName() + 
						"\nPNR: " + customerList.get(i).getPnr() + 
						"\nHotel Room:" + customerList.get(i).getHotelRoom() +
						"\nHotel ID: " + customerList.get(i).getHotelId() +
						"\nPerson Count" + customerList.get(i).getPersonCount() +
						"\nTimestamp:" + getTimestamp(pnr);
			}
		}
		return null;
	}
	
	public String reserveRoom(int pnr, int personCount, int hotelId, String customerName, VectorTimestamp vt) {
		if (hotelId > hotelList.size() || hotelId < 0) {
			return null;
		}
		
		int response = hotelList.get(hotelId).addCustomer(pnr);
		if (response == -1)
			return null;
		
		
		timestamps.add(new PassengerTimestamp(pnr, vt));
		timestamps.get(timestamps.size()-1).increaseTimestamp(1);
		customerList.add(new HotelReservation(pnr, response, hotelId, customerName, personCount));
		return "Customer Name: " + customerList.get(customerList.size()-1).getPassengerName() + 
				"\nPNR: " + customerList.get(customerList.size()-1).getPnr() + 
				"\nHotel Room:" + customerList.get(customerList.size()-1).getHotelRoom() +
				"\nHotel ID: " + customerList.get(customerList.size()-1).getHotelId() +
				"\nPerson Count:" + customerList.get(customerList.size()-1).getPersonCount() +
				"\nTimestamp:" + getTimestamp(pnr);
	}
	
	public boolean deleteRoom(int pnr) {
		for (int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getPnr() == pnr){
				//TODO Reset timestamp?
				Hotel hotel = hotelList.get(customerList.get(i).getHotelId());
				hotel.removeCustomer(pnr, customerList.get(i).getHotelRoom());
				timestamps.get(getPassengerTimestamp(pnr)).resetTimestamp(1);
				return true;
			}
		}
		return false;
	}
	
	public int getPassengerTimestamp(int pnr) {
		for (int i = 0; i < timestamps.size(); i++) {
			if (timestamps.get(i).getPnr() == pnr)
				return i;
		}
		return -1;
	}
	
	public String getTimestamp(int pnr) {
		VectorTimestamp timestamp = timestamps.get(getPassengerTimestamp(pnr)).getTimestamp();
		String vts = "";
		int[] dummy = timestamp.getTimestamp();
		for (int i = 0; i < timestamp.getTimestamp().length; i++) {
			if (i == 0) 
				vts += dummy[i];
			else
			vts += ", " + dummy[i];
		}
		return vts;
	}
}
