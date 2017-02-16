package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

privileged aspect CurrencyExchange_Redis_Entity {

	declare @type: CurrencyExchange :@RedisHash;
	//FIXME why when this is public is not accessible?
	public static final String CurrencyExchange.KEY = "currency:list";

	public static final String CurrencyExchange.key() {
		return KEY;
	}

	public static final String CurrencyExchange.key(String id) {
		String pattern = KEY + ":%s";
		return String.format(pattern, id);
	}

	public static final String CurrencyExchange.Key(String id) {
		return KEY + ":" + id;
	}

	@Id
	private String CurrencyExchange.key;

	public String CurrencyExchange.getKey() {
		return key;
	}

	public void CurrencyExchange.setKey(String key) {
		this.key = key;
	}
}
