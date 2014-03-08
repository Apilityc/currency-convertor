package org.apilytc.domain;

privileged aspect CurrencyExchange_Redis_Entity {

	public static final String CurrencyExchange.KEY = "currency:list";

	public static final String CurrencyExchange.key() {
		return KEY;
	}

	private String CurrencyExchange.key;

	private String CurrencyExchange.getKey() {
		return key;
	}

	private void CurrencyExchange.setKey(String key) {
		this.key = key;
	}
}
