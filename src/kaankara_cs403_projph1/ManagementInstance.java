package kaankara_cs403_projph1;

public class ManagementInstance {
	
	private static FlightManagement management = new FlightManagement();
	
	public static FlightManagement getManagementInstance(){
		return management;
	}
}
