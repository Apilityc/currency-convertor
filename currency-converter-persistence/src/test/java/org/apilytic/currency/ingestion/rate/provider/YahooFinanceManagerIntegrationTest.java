package org.apilytic.currency.ingestion.rate.provider;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

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
