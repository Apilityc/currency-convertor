package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("indicators")
public class YahooIndicators {

	private List<YahooAdjClose> adjclose;

	public List<YahooAdjClose> getAdjclose() {
		return adjclose;
	}
}
