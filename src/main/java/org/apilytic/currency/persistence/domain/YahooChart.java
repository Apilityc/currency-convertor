package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("chart")
public class YahooChart {

	private List<YahooResult> result;

	public List<YahooResult> getResult() {
		return result;
	}

	public static class Holder {
		private YahooChart chart;

		public YahooChart getChart() {
			return chart;
		}
	}
}

