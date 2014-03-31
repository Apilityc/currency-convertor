package org.apilytic.currency.ingestion.rate.provider;

import java.util.List;

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
	public List<? extends ExchangeRate> provideRate();

	/**
	 * Sets exchange query string for currency services
	 * 
	 * @param exchangeQuery
	 */
	public void setExchangeQuery(String exchangeQuery);

}
