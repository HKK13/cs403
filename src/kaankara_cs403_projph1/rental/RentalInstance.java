package kaankara_cs403_projph1.rental;

public class RentalInstance {
	static RentalManagement management = new RentalManagement();
	
	public static RentalManagement getInstance() {
		return management;
	}
}
