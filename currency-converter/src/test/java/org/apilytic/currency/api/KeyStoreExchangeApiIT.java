package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.Test;

/**
 * 
 * @author g
 *
 */
public class KeyStoreExchangeApiIT {

	private static final String EXCHANGE_API = "keyStoreExchangeApi";
	
	@Autowired
	@Qualifier(EXCHANGE_API)
	private CurrencyExchangeApi keyStoreExchangeApi;
	
	@Autowired
	@Qualifier(EXCHANGE_API)
	private CurrencyExchangeApi optionalCurrencyExchangeApi;

	@Test
	public void createInstanceByReflection() {
		assertNotNull(keyStoreExchangeApi);
		assertTrue(keyStoreExchangeApi instanceof KeyStoreExchangeApi);
	}
	
	@Test
	public void createOptionalInstance() {
		assertNotNull(optionalCurrencyExchangeApi);
		assertTrue(optionalCurrencyExchangeApi instanceof KeyStoreExchangeApi);
	}
	
	@Test
	public void calculateExchange() {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setAmount("5.45");
		currencyRate.setFromCurrency("EUR");
		currencyRate.setToCurrency("GBP");
		
		System.out.println(">>> " + keyStoreExchangeApi);
		ExchangeCurrency exchange = keyStoreExchangeApi.exchangeSingleCurrency(currencyRate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}

}
