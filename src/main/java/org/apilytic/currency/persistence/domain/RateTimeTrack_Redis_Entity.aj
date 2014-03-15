package org.apilytic.currency.persistence.domain;

public privileged aspect RateTimeTrack_Redis_Entity {

	private String RateTimeTrack.key;
	
	public static String RateTimeTrack.KEY = "rate:time-track";
	
	public static String RateTimeTrack.key() {
		return  KEY;
	}

	public void RateTimeTrack.setKey(String key) {
		this.key = key;
	}
	
	public String RateTimeTrack.getKey() {
		return this.key;
	}
	
}
