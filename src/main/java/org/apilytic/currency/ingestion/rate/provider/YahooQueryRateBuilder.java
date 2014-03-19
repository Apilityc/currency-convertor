package org.apilytic.currency.ingestion.rate.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Create yahoo query rate from exchange rates.
 * 
 * @author Georgi Lambov
 * @param <ExchangeRate>
 * 
 */
@Service
public class YahooQueryRateBuilder {

	@Autowired
	private YahooFinanceManager financialProvider;

	/**
	 * Builds yahoo query rate builder
	 * 
	 * @return
	 */
	public String createQueryRate() {

		return null;
	}

}
