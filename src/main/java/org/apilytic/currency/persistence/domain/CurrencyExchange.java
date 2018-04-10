package org.apilytic.currency.persistence.domain;


import java.util.Set;

/**
 * Created by g on 4/13/17.
 */
public class CurrencyExchange {
	private Set<String> titles;

	public Set getTitles() {
		return titles;
	}

	public void setTitles(Set titles) {
		this.titles = titles;
	}
}
