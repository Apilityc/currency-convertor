package org.apilytic.currency.persistence.domain;

import java.util.Map;

/**
 * Holds currency exchange rates.
 */
public class Exchange {
	private Map<String, String> rates;

	public Map<String, String> getRates() {
		return rates;
	}

	public void setRates(Map<String, String> rates) {
		this.rates = rates;
	}
}
