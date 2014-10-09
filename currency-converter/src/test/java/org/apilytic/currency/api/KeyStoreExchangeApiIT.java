package org.apilytic.currency.api;

import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;
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
	private RateRepository rateRepository;
	
	@Autowired
	@Qualifier(EXCHANGE_API)
	private CurrencyExchangeApi optionalCurrencyExchangeApi;

	@BeforeClass
	public void init() {
		Map<String, String> value = new HashMap<String, String>();
		value.put("GBP", "5.45");
		
		Rate rate = new Rate();
		rate.setKey(Rate.key("EUR"));
		rate.setValue(value);
		rateRepository.save(rate);
	}
	
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
		
		ExchangeCurrency exchange = keyStoreExchangeApi.exchangeSingleCurrency(currencyRate);
		assertNotNull(exchange.getExchange());
		assertTrue(exchange.getExchange().equals(0) == false);
		assertTrue(Double.valueOf(exchange.getExchange()) > 0);
	}
	
	@Test
	public void calculateExchangeWithLocaleFormat() {
		CurrencyRate currencyRate = new CurrencyRate();
		currencyRate.setAmount("5.45");
		currencyRate.setFromCurrency("EUR");
		currencyRate.setToCurrency("GBP");
		currencyRate.setLocale("en_US");
		
		ExchangeCurrency exchange = keyStoreExchangeApi.exchangeSingleCurrency(currencyRate);
		assertTrue(exchange.getExchange().startsWith("$"));
		assertTrue(Double.valueOf(exchange.getExchange().replace("$","")) > 0);
	}

}
