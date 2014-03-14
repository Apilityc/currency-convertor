package org.apilytc.currency.ingestion.rate.provider;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooFinanceIntegrationTest {

	@Autowired
	@Qualifier("YahooFiananceProvider")
	private FinancialProvider finanacialProvider;

	@Test
	public void testProvideRateWithEurToUSDQueryRate() {
		finanacialProvider.setExchangeQuery("&s=USDEUR=X");
		String provideRate = finanacialProvider.provideRate();
		assertTrue(provideRate.startsWith("\"USDEUR=X"));
	}

}
