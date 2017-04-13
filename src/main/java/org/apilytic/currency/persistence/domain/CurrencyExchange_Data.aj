package org.apilytic.currency.persistence.domain;

/**
 * Created by g on 4/13/17.
 */
public aspect CurrencyExchange_Data {
	public String CurrencyExchange.getTitle() {
		return title;
	}

	public void CurrencyExchange.setTitle(String title) {
		this.title = title;
	}

}
