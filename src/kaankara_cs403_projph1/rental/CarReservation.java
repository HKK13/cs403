package kaankara_cs403_projph1.rental;

public class CarReservation {
	private int carId, pnr;
	private String passengerName;
	
	public CarReservation(int carId, int pnr, String passengerName) {
		this.carId = carId;
		this.pnr = pnr;
		this.passengerName = passengerName;
	}

	public int getCarId() {
		return carId;
	}

	public int getPnr() {
		return pnr;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setCarId(int carId) {
		this.carId = carId;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
}
