package kaankara_cs403_projph1.timestamp;

public class VectorTimestamp {
	private int[] timestamp;
	
	public VectorTimestamp(int length) {
		timestamp = new int[length];
	}
	
	/**
	 * Increases specified part of the timestamp.
	 * 0 = flight
	 * 1 = hotel
	 * 2 = car rental
	 * @param part
	 */
	public void increaseTimeOf(int part) {
		timestamp[part]++;
	}
	
	/**
	 * Returns the recent timestamp.
	 * @return
	 */
	public int[] getTimestamp() {
		return timestamp;
	}
}
