package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

public privileged aspect RateTimeTrack_Redis_Entity {

	declare @type: CurrencyExchange :@RedisHash;

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
