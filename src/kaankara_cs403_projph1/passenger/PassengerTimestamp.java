package kaankara_cs403_projph1.passenger;

import kaankara_cs403_projph1.timestamp.VectorTimestamp;

public class PassengerTimestamp {

	private int pnr;
	private VectorTimestamp timestamp;
	
	public PassengerTimestamp(int pnr, VectorTimestamp timestamp) {
		this.pnr = pnr;
		this.timestamp = timestamp;
	}

	public int getPnr() {
		return pnr;
	}

	public VectorTimestamp getTimestamp() {
		return timestamp;
	}

	public void setPnr(int pnr) {
		this.pnr = pnr;
	}

	public void setTimestamp(VectorTimestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	public void increaseTimestamp(int region) {
		this.timestamp.increaseTimeOf(region);
	}
	
	public void resetTimestamp(int region) {
		this.timestamp.resetTimestamp(region);
	}
}
