package kaankara_cs403_projph1.hotel;

public class HotelManagementInstance {
	static HotelManagement management = new HotelManagement();
	
	public static HotelManagement getInstance() {
		return management;
	}
}
