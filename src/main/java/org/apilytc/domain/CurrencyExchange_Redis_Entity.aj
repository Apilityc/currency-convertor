package org.apilytc.domain;


privileged aspect CurrencyExchange_Redis_Entity {

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

	private String CurrencyExchange.key;

	public String CurrencyExchange.getKey() {
		return key;
	}

	public void CurrencyExchange.setKey(String key) {
		this.key = key;
	}
}
