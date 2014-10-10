package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.testng.annotations.Test;

/**
 * 
 * @author g
 *
 */
privileged aspect CurrencyExchangeApiIT {

	@Test(dataProvider = "exchangeLocalRates")
	public void KeyStoreExchangeApiIT.calculateExchange(CurrencyRate rate) {
		ExchangeCurrency exchange = currencyExchangeApi
				.exchangeSingleCurrency(rate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}

	@Test(dataProvider = "exchangeLocalRates")
	public void KeyStoreExchangeApiIT.calculateExchangeWithLocaleFormat(
			CurrencyRate rate) {
		ExchangeCurrency exchange = currencyExchangeApi
				.exchangeSingleCurrency(rate);
		assertTrue(exchange.getExchange().startsWith("$"));
		assertTrue(Double.valueOf(exchange.getExchange().replace("$", "")) > 0);
	}
	
	@Test(dataProvider = "exchangeLocalRates")
	public void DirectDealExchangeApiIT.calculateExchange(CurrencyRate rate) {
		ExchangeCurrency exchange = currencyExchangeApi
				.exchangeSingleCurrency(rate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}

	@Test(dataProvider = "exchangeLocalRates")
	public void DirectDealExchangeApiIT.calculateExchangeWithLocaleFormat(
			CurrencyRate rate) {
		ExchangeCurrency exchange = currencyExchangeApi
				.exchangeSingleCurrency(rate);
		assertTrue(exchange.getExchange().startsWith("$"));
		assertTrue(Double.valueOf(exchange.getExchange().replace("$", "")) > 0);
	}
}
