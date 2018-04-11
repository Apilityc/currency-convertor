package org.apilytic.currency.persistence.domain;


import java.util.Set;

/**
 * Created by g on 4/13/17.
 */
public class CurrencyExchange {
	private Set<String> codes;

	public Set getCodes() {
		return codes;
	}

	public void setCodes(Set codes) {
		this.codes = codes;
	}
}
