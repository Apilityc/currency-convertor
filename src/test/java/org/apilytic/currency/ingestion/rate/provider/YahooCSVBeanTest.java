package org.apilytic.currency.ingestion.rate.provider;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooCSVBeanTest {

	private YahooCSVBean csvBean;

	@Before
	public void init() {
		csvBean = new YahooCSVBean();
	}

	@Test
	public void testToCurrencyValidCurrencyFromYahoo() {

		csvBean.setCurrency(String.format(
				YahooQueryRateBuilder.queryRatePattern, "USD", "EUR"));
		assertEquals("USD", csvBean.fromCurrency());
		assertEquals("EUR", csvBean.toCurrency());
	}

	@Test
	public void testRate() {
		csvBean.setRate("12.02");
		assertEquals("12.02", csvBean.rate());
	}
}
