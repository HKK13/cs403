package kaankara_cs403_projph1.rental;

import java.util.ArrayList;

import kaankara_cs403_projph1.passenger.PassengerTimestamp;
import kaankara_cs403_projph1.timestamp.VectorTimestamp;

public class RentalManagement {
	private int[] carList;
	private ArrayList<CarReservation> customerList;
	private ArrayList<PassengerTimestamp> timestamps;
	
	public RentalManagement() {
		carList = new int[250];
		customerList = new ArrayList<>();
		timestamps = new ArrayList<>();
	}
	
	public String reserveCar(int pnr, int carId, String customerName, VectorTimestamp vt) {
		if (carId > carList.length || carId < 0) {
			return null;
		}
		
		if (carList[carId] == 1)
			return null;
		
		carList[carId] = 1;
		timestamps.add(new PassengerTimestamp(pnr, vt));
		timestamps.get(timestamps.size()-1).increaseTimestamp(2);
		customerList.add(new CarReservation(carId, pnr, customerName));
		return "Customer Name: " + customerList.get(customerList.size()-1).getPassengerName() + 
				"\nPNR: " + customerList.get(customerList.size()-1).getPnr() + 
				"\nCar ID: " + customerList.get(customerList.size()-1).getCarId() +
				"\nTimestamp:" + getTimestamp(pnr);
	}
	
	public String getReservation(int pnr) {
		for(int i = 0; i < customerList.size(); i++) {
			if (customerList.get(i).getPnr() == pnr) {
				return "Customer Name: " + customerList.get(i).getPassengerName() + 
						"\nPNR: " + customerList.get(i).getPnr() + 
						"\nCar ID: " + customerList.get(i).getCarId() +
						"\nTimestamp:" + getTimestamp(pnr);
			}
		}
		return null;
	}
	
	public boolean deleteCar(int pnr) {
		for (int i = 0; i < customerList.size(); i++) {
			if(customerList.get(i).getPnr() == pnr){
				//TODO Reset timestamp?
				carList[i] = 0;
				customerList.remove(i);
				timestamps.get(getPassengerTimestamp(pnr)).resetTimestamp(2);
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
