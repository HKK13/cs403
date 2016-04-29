package kaankara_cs403_projph1.timestamp;

public class VectorTimestamp {
	private int[] timestamp;
	
	public VectorTimestamp(int length) {
		timestamp = new int[length];
	}
	
	public VectorTimestamp(int[] timestamp) {
		this.timestamp = timestamp; 
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
	
	/**
	 * Checks whether two VectorTimestamps are equal.
	 * @param timestamp
	 * @return
	 */
	public boolean isEqual(VectorTimestamp timestamp) {
		
		if(timestamp.getTimestamp() != null && timestamp.getTimestamp().length != this.timestamp.length) {
			return false;
		}
		
		int[] dummyTimestamp = timestamp.getTimestamp();
		for (int i = 0; i < this.timestamp.length; i++) {
			if (this.timestamp[i] != dummyTimestamp[i])
				return false;
		}
		
		return true;
	}
}
