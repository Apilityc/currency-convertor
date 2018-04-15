package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

privileged aspect Currency_Entity {
	@Id
	String Currency.id;

	public String Currency.getId() {
		return id;
	}

	public void Currency.setId(String id) {
		this.id = id;
	}
}
