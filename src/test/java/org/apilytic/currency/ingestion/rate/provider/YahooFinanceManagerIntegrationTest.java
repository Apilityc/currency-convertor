package org.apilytic.currency.ingestion.rate.provider;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooFinanceManagerIntegrationTest {

	@Autowired
	private FinancialProvider finanacialProvider;

	@Test
	public void testProvideRateWithEurToUSDQueryRate() {
		finanacialProvider.setExchangeQuery("&s=USDEUR=X");
		List<? extends ExchangeRate> providedRates = finanacialProvider
				.provideRate();

		assertEquals(1, providedRates.size());
		assertEquals("USD", providedRates.get(0).fromCurrency());
		assertEquals("EUR", providedRates.get(0).toCurrency());
		assertNotNull(providedRates.get(0).rate());
	}
}
