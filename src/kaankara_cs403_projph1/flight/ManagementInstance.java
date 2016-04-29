package kaankara_cs403_projph1.flight;

public class ManagementInstance {
	
	private static FlightManagement management = new FlightManagement();
	
	public static FlightManagement getManagementInstance(){
		return management;
	}
}
