package org.apilytic.currency.service;

import org.apilytic.currency.ingestion.rate.RateIngestion;
import org.apilytic.currency.ingestion.vapor.VaporIngestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class CurrencyRateIngestionService {

	@Autowired
	private RateIngestion rateIngestion;

	@Autowired
	private VaporIngestion vaporIngestion;

	/**
	 * Updates countries' currencies and rates.
	 * 
	 * @throws InterruptedException
	 */
	public void syncCountryCurrenciesAndRates() throws InterruptedException {
		vaporIngestion.sync();
		rateIngestion.sync();
	}
}
