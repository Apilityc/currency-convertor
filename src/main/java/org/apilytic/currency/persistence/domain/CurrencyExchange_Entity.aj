package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by g on 4/14/17.
 */
public aspect CurrencyExchange_Entity {
	@Id
	String CurrencyExchange.id;

	public String CurrencyExchange.getId() {
		return id;
	}
}
