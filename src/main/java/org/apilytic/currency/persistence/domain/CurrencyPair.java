package org.apilytic.currency.persistence.domain;

public class CurrencyPair {

	private String from;

	private String to;

	public String from() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String to() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}
}
