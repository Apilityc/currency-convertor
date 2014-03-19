package org.apilytic.currency.ingestion.rate.provider;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apilytic.currency.exchange.ExchangeRate;
import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/***
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooQueryRateBuilderTest {

	private YahooQueryRateBuilder queryRateBuilder;

	@Mock
	private ExchangeRate exchangeRate;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		queryRateBuilder = new YahooQueryRateBuilder();
		ReflectionTestUtils.setField(queryRateBuilder, "exchangeRate",
				exchangeRate);
	}

	@Test
	public void buildQueryRate() {
		when(exchangeRate.getRates()).thenReturn(dataProviderForRates());

		queryRateBuilder.createQueryRate();

		verify(exchangeRate.getRates());
	}

	private Map<String, Set<CurrencyExchange>> dataProviderForRates() {
		CurrencyExchange usd = new CurrencyExchange();
		usd.setTitle("USD");

		CurrencyExchange eur = new CurrencyExchange();
		usd.setTitle("EUR");

		CurrencyExchange gbp = new CurrencyExchange();
		usd.setTitle("GBP");

		Map<String, Set<CurrencyExchange>> rates = new HashMap<String, Set<CurrencyExchange>>();
		rates.put("USD", new HashSet<CurrencyExchange>(Arrays.asList(eur, gbp)));
		rates.put("GBP", new HashSet<CurrencyExchange>(Arrays.asList(usd, eur)));
		rates.put("EUR", new HashSet<CurrencyExchange>(Arrays.asList(usd, gbp)));

		return rates;
	}
}
