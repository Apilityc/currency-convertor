package org.apilytic.currency.ingestion.rate;

import java.util.List;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.FinancialProvider;

/**
 * Sync currencies to database.
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooExchangeService implements RateIngestion {

	private FinancialProvider financialProvider;

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

	}

	public void setFinancialProvider(FinancialProvider financialProvider) {
		this.financialProvider = financialProvider;
	}
}
