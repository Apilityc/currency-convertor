package org.apilytic.currency.persistence.domain;


import java.util.Set;

/**
 * Stores supported currencies.
 */
public class Currency {
	private Set<String> codes;

	public Set getCodes() {
		return codes;
	}

	public void setCodes(Set codes) {
		this.codes = codes;
	}
}
