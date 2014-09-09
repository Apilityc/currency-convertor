package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.AssertJUnit.assertNotNull;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 
 * @author g
 *
 */
public class DirectDealExchangeApiIT {

	
	@Autowired
	private CurrencyExchangeApi directDealExchangeApi;
	@Autowired
	private CurrencyExchangeApi defaultCurrencyExchangeApi;
	
	@Test
	public void createInstanceByReflection() {
		assertNotNull(directDealExchangeApi);
		assertTrue(directDealExchangeApi instanceof DirectDealExchangeApi);
	}
	
	@Test
	public void createDefaultInstance() {
		assertNotNull(defaultCurrencyExchangeApi);
		assertTrue(defaultCurrencyExchangeApi instanceof DirectDealExchangeApi);
	}
	
	@Test
	public void calculateExchange() {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setAmount("5.45");
		currencyRate.setFromCurrency("USD");
		currencyRate.setToCurrency("EUR");
		
		ExchangeCurrency exchange = directDealExchangeApi.exchangeSingleCurrency(currencyRate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}
	
	@Test
	public void calculateExchangeWithLocaleFormat() {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setAmount("5.45");
		currencyRate.setFromCurrency("USD");
		currencyRate.setToCurrency("EUR");
		currencyRate.setLocale("en_US");
		
		ExchangeCurrency exchange = directDealExchangeApi.exchangeSingleCurrency(currencyRate);
		assertTrue(exchange.getExchange().startsWith("$"));
		assertTrue(Double.valueOf(exchange.getExchange().replace("$","")) > 0);
		
	}
	
}
