package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

privileged aspect Rate_Redis_Entity {

	declare @type: CurrencyExchange :@RedisHash;

	private static final String Rate.KEY = "query:%s";

	public static final String Rate.key(String key) {
		return String.format(Rate.KEY, key);
	}

	private String Rate.key;

	public String Rate.getKey() {
		return this.key;
	}

	public void Rate.setKey(String key) {
		this.key = key;

	}
}
