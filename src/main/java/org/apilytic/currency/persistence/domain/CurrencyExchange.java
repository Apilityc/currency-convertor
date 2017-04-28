package org.apilytic.currency.persistence.domain;


import java.util.Set;

/**
 * Created by g on 4/13/17.
 */
public class CurrencyExchange {
	private Set<String> titles;

	public Set<String> getTitles() {
		return titles;
	}

	public void setTitles(Set<String> titles) {
		this.titles = titles;
	}
}
