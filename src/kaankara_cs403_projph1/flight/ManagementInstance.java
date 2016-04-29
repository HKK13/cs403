package kaankara_cs403_projph1.flight;

public class ManagementInstance {
	
	private static FlightManagement management = new FlightManagement();
	
	/**
	 * Provide singleton usage to FlightManagement Object.
	 * @return
	 */
	public static FlightManagement getManagementInstance(){
		return management;
	}
}
