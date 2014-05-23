package org.apilytic.currency.ingestion.rate.provider;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public interface ExchangeRate {

	/**
	 * 
	 * @return
	 */
	String fromCurrency();

	/**
	 * 
	 * @return
	 */
	String toCurrency();

	/**
	 * 
	 * @return
	 */
	String rate();
}
