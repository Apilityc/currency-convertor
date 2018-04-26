package org.apilytic.currency.persistence.domain;


import java.util.Set;

/**
 * Stores supported currencies.
 */
public class Currency {
	private Set<String> codes;

	public Set<String> getCodes() {
		return codes;
	}

	public void setCodes(Set<String> codes) {
		this.codes = codes;
	}
}
