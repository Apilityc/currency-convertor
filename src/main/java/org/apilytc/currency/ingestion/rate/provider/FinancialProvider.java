package org.apilytc.currency.ingestion.rate.provider;

/**
 * Provide currency rates.
 * 
 * @author Georgi Lambov
 * 
 */
public interface FinancialProvider {

	/**
	 * Retrieves cross-rates for currencies.
	 * 
	 * @return
	 */
	public String provideRate();

	/**
	 * Sets exchange query string for currency services
	 * 
	 * @param exchangeQuery
	 */
	public void setExchangeQuery(String exchangeQuery);

}
