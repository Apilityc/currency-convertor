package org.apilytic.currency.persistence.domain;

public privileged aspect RateTimeTrack_Redis_Entity {

	private String RateTimeTrack.key;
	
	private static final String RateTimeTrack.KEY = "query:timetrack";
	
	public static final String RateTimeTrack.key() {
		return  KEY;
	}

	public void RateTimeTrack.setKey(String key) {
		this.key = key;
	}
	
	public String RateTimeTrack.getKey() {
		return this.key;
	}
	
}
