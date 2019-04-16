package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DuckDuckGoChart {

	private Conversion conversion;

	public Conversion getConversion() {
		return conversion;
	}

	@JsonIgnoreProperties(ignoreUnknown = true)
	public static class Conversion {
		@JsonProperty("converted-amount")
		private String convertedAmount;

		public String getConvertedAmount() {
			return convertedAmount;
		}
	}
}
