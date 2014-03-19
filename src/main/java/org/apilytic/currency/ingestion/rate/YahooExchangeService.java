package org.apilytic.currency.ingestion.rate;

import java.util.List;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sync currencies to database.
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class YahooExchangeService implements RateIngestion {

	@Autowired
	private YahooFinanceManager financialProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apilytic.currency.ingestion.rate.RateIngestion#sync()
	 */
	@Override
	public void sync() {
		financialProvider.setExchangeQuery("");
		List<? extends ExchangeRate> providedRates = financialProvider
				.provideRate();

		providedRates.size();

	}
}
