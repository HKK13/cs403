package kaankara_cs403_projph1.hotel;

public class HotelReservation {
	private int pnr, hotelRoom, hotelId, personCount;
	private String passengerName;

	public HotelReservation(int pnr, int hotelRoom, int hotelId, 
			String passengerName, int personCount) {
		this.pnr = pnr;
		this.hotelRoom = hotelRoom;
		this.hotelId = hotelId;
		this.passengerName = passengerName;
		this.personCount = personCount;
	}

	public int getPnr() {
		return pnr;
	}

	public int getHotelRoom() {
		return hotelRoom;
	}

	public int getHotelId() {
		return hotelId;
	}

	public int getPersonCount() {
		return personCount;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public void setHotelRoom(int hotelRoom) {
		this.hotelRoom = hotelRoom;
	}

	public void setHotelId(int hotelId) {
		this.hotelId = hotelId;
	}

	public void setPersonCount(int personCount) {
		this.personCount = personCount;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}
}
