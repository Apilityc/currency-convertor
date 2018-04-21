package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

@JsonRootName("result")
public class YahooResult {

	private YahooMeta meta;
	private YahooIndicators indicators;

	public YahooIndicators getIndicators() {
		return indicators;
	}

	public YahooMeta getMeta() {
		return meta;
	}
}
