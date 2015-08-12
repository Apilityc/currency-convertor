package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

privileged aspect CurrencyExchange_Redis_Entity {

	//FIXME why when this is public is not accessible?
	private static final String CurrencyExchange.KEY = "currency:list";

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
