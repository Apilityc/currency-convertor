package org.apilytic.currency.persistence.domain;

import org.springframework.data.annotation.Id;

privileged aspect Exchange_Entity {
	@Id
	String Exchange.id;

	public String Exchange.getId() {
		return id;
	}

	public void Exchange.setId(String id) {
		this.id = id;
	}
}
