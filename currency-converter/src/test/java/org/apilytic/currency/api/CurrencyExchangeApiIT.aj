package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.aspectj.lang.annotation.Before;
import org.testng.annotations.Test;

/**
 * 
 * @author g
 *
 */
privileged aspect CurrencyExchangeApiIT {

	@Test(dataProvider = "exchangeLocalRates")
	public void KeyStoreExchangeApiIT.calculateExchange(CurrencyRate rate) {
	
	}

	@Test(dataProvider = "exchangeLocalRates")
	public void KeyStoreExchangeApiIT.calculateExchangeWithLocaleFormat(
			CurrencyRate rate) {
	}
	
	@Test(dataProvider = "exchangeLocalRates")
	public void DirectDealExchangeApiIT.calculateExchange(CurrencyRate rate) {
		
	}

	@Test(dataProvider = "exchangeLocalRates")
	public void DirectDealExchangeApiIT.calculateExchangeWithLocaleFormat(
			CurrencyRate rate) {
	}
	
	@Before("execution(* org.apilytic.currency.api.*IT.calculateExchange(..)) && target(p) && args(rate)")
	public void calculateExchange(CurrencyExchangeApi p, CurrencyRate rate) {
		ExchangeCurrency exchange = p
				.exchangeSingleCurrency(rate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}
	
	@Before("execution(* org.apilytic.currency.api.*IT.calculateExchange(..)) && target(p) && args(rate)")
	public void calculateExchangeWithLocaleFormat(CurrencyExchangeApi p, CurrencyRate rate) { 
		ExchangeCurrency exchange = p
				.exchangeSingleCurrency(rate);
		assertTrue(exchange.getExchange().startsWith("$"));
		assertTrue(Double.valueOf(exchange.getExchange().replace("$", "")) > 0);
	}

}
