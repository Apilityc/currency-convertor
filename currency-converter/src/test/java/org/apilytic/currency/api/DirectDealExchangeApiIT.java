package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 
 * @author g
 *
 */
public class DirectDealExchangeApiIT {

	@Autowired
	private CurrencyExchangeApi currencyExchangeApi;
	@Autowired
	private CurrencyExchangeApi defaultCurrencyExchangeApi;

	@Test
	public void createInstanceByReflection() {
		assertNotNull(currencyExchangeApi);
		assertTrue(currencyExchangeApi instanceof DirectDealExchangeApi);
	}

	@Test
	public void createDefaultInstance() {
		assertNotNull(defaultCurrencyExchangeApi);
		assertTrue(defaultCurrencyExchangeApi instanceof DirectDealExchangeApi);
	}
}
