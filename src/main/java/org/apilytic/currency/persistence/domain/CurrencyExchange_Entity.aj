package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

/**
 * Created by g on 4/14/17.
 */
privileged aspect CurrencyExchange_Entity {
	@Id
	String CurrencyExchange.id;

	public String CurrencyExchange.getId() {
		return id;
	}

	public void CurrencyExchange.setId(String id) {
		this.id = id;
	}
}
