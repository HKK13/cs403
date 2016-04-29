package kaankara_cs403_projph1.hotel;

import java.util.ArrayList;

public class Hotel {
	private int hotelLimit, emptyRooms, hotelId;
	ArrayList<Integer> customerList;
	int[] roomList;
	
	public Hotel(int hotelId) {
		this.hotelId = hotelId;
		this.hotelLimit = 250;
		this.emptyRooms = 250;
		customerList = new ArrayList<>();
		roomList = new int[250];
	}
	
	public int addCustomer(int pnr){
		this.emptyRooms--;
		if (!customerList.contains(pnr))
			this.customerList.add(pnr);
		else return -1;
		return generateRoomNumber();
	}
	
	public int generateRoomNumber() {
		if (this.emptyRooms > 0) {
			for (int i = 0; i < this.roomList.length; i++) {
				if(roomList[i] == 0){
					roomList[i] = 1;
					return i;
				}
			}
		}
		return -2;
	}
	
	public void removeCustomer(int pnr, int roomNumber) {
		customerList.remove((Integer)pnr);
		roomList[roomNumber] = 0;
	}
}
