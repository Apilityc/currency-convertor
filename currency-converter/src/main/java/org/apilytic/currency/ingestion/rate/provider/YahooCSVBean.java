package org.apilytic.currency.ingestion.rate.provider;

/**
 * Implementation for Yahoo financial provider.
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooCSVBean implements ExchangeRate {

	public static final String FIELD_CURRENCY = "currency";
	public static final String FIELD_RATE = "query";

	private String fromCurrency;
	private String toCurrency;
	private String rate;

	/**
	 * Sets fromCurrency and toCurrency based on values of
	 * {@link YahooCSVBean#FIELD_CURRENCY}.
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		fromCurrency = currency.substring(0, 3);
		toCurrency = currency.substring(3, 6);
	}

	/**
	 * Sets exchange query between fromCurrency and toCurrency from
	 * {@link YahooCSVBean#FIELD_RATE}.
	 * 
	 * @param rate
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apilytic.currency.ingestion.query.provider.FinancialProviderBean#
	 * fromCurrency()
	 */
	@Override
	public String fromCurrency() {
		return this.fromCurrency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apilytic.currency.ingestion.query.provider.FinancialProviderBean#
	 * toCurrency()
	 */
	@Override
	public String toCurrency() {
		return this.toCurrency;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apilytic.currency.ingestion.query.provider.FinancialProviderBean#query
	 * ()
	 */
	@Override
	public String rate() {
		return this.rate;
	}

}
