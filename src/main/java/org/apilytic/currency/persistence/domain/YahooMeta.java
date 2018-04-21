package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("mata")
public class YahooMeta {

	private String currency;

	public String getCurrency() {
		return currency;
	}
}
