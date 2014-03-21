package org.apilytic.currency.ingestion.rate.provider;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import org.apilytic.currency.exchange.ExchangeRate;
import org.apilytic.currency.exchange.ExchangeRateBuilder;
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
	private ExchangeRateBuilder exchangeRateBuilder;

	@Mock
	private ExchangeRate exchangeRate;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		queryRateBuilder = new YahooQueryRateBuilder();
		ReflectionTestUtils.setField(queryRateBuilder, "exchangeRateBuilder",
				exchangeRateBuilder);
	}

	@Test
	public void buildQueryRateWithOneExchangeRate() {
		when(exchangeRateBuilder.getExchangeRate()).thenReturn(
				dataProviderForRates(1));

		String queryRate = queryRateBuilder.createQueryRate();
		assertEquals("&s=USDEUR=X&s=USDGBP=X", queryRate);

		verify(exchangeRateBuilder).constructExchageRate();
	}

	private ExchangeRate dataProviderForRates(int numberOfRates) {
		CurrencyExchange usd = new CurrencyExchange();
		usd.setTitle("USD");

		CurrencyExchange eur = new CurrencyExchange();
		eur.setTitle("EUR");

		CurrencyExchange gbp = new CurrencyExchange();
		gbp.setTitle("GBP");

		Map<String, Set<CurrencyExchange>> rates = new HashMap<String, Set<CurrencyExchange>>();
		rates.put("USD",
				new LinkedHashSet<CurrencyExchange>(Arrays.asList(eur, gbp)));

		ExchangeRate exchageRate = new ExchangeRate();
		if (numberOfRates == 1) {
			exchageRate.setRates(rates);

			return exchageRate;
		}

		rates.put("GBP", new HashSet<CurrencyExchange>(Arrays.asList(usd, eur)));
		rates.put("EUR", new HashSet<CurrencyExchange>(Arrays.asList(usd, gbp)));

		exchageRate.setRates(rates);

		return exchageRate;
	}
}
