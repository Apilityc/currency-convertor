package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

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
