package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("chart")
public class YahooChart {

	private List<Result> result;

	public List<Result> getResult() {
		return result;
	}

	public static class Holder {
		private YahooChart chart;

		public YahooChart getChart() {
			return chart;
		}
	}

	public static class Result {

		private Meta meta;
		private YahooIndicators indicators;

		public YahooIndicators getIndicators() {
			return indicators;
		}

		public Meta getMeta() {
			return meta;
		}

		public static class Meta {

			private String currency;

			public String getCurrency() {
				return currency;
			}
		}
	}
}

