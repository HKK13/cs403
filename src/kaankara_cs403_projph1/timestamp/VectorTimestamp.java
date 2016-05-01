package kaankara_cs403_projph1.timestamp;

public class VectorTimestamp {
	private int[] timestamp;
	
	public VectorTimestamp(int length) {
		timestamp = new int[length];
	}
	
	public VectorTimestamp(int[] timestamp) {
		this.timestamp = timestamp; 
	}
	
	public VectorTimestamp(String timestamp) {
		String[] dum = timestamp.split(",");
		int[] ts = new int[dum.length];
		for (int i = 0; i < ts.length; i++) {
			ts[i] = Integer.parseInt(dum[i]);
		}
		this.timestamp = ts;
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
	
	public void resetTimestamp(int region) {
		this.timestamp[region] = 0;
	}
	
	/**
	 * Checks whether two VectorTimestamps are equal.
	 * @param timestamp
	 * @return
	 */
	public boolean isEqual(VectorTimestamp timestamp) {
		
		if(timestamp.getTimestamp() != null && timestamp.getTimestamp().length != this.timestamp.length&& IsConcurrent(timestamp)==false) {
			return false;
		}
		
		int[] dummyTimestamp = timestamp.getTimestamp();
		for (int i = 0; i < this.timestamp.length; i++) {
			if (this.timestamp[i] != dummyTimestamp[i])
				return false;
		}
		
		return true;
	}
	
	public boolean IsConcurrent(VectorTimestamp timestamp)
	{
		boolean greater=false, less=false;
		int[] v = timestamp.getTimestamp();
		int[] w = this.getTimestamp();

		for (int i=0; i < this.timestamp.length; i++) 
			if (v[i] < w[i])
				greater = true;
			else
				less = true;
		if (greater && less)
			return true;	
		else
			return false;
	}

	
}
