package org.apilytic.currency.exchange;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

public class ExchangeRateBuilderTest {

	private ExchangeRateBuilder builder;

	@Mock
	private CurrencyExchangeRepository currencyExchangeRepo;

	private boolean runOnce = false;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		if (runOnce) {
			return;
		}

		runOnce = true;

		builder = new ExchangeRateBuilder();
		ReflectionTestUtils.setField(builder, "currencyExchangeRepo",
				currencyExchangeRepo);
	}

	@Test
	public void testBuildOfExchangeRate() {
		when(currencyExchangeRepo.findAllCurencyExchanges()).thenReturn(
				currencyData());

		builder.constructExchageRate();

		Map<String, Set<CurrencyExchange>> rates = builder.getExchangeRate()
				.getRates();

		assertEquals(2, rates.get("USD").size());
		assertEquals(2, rates.get("EUR").size());
		assertEquals(2, rates.get("GBP").size());

		for (String key : rates.keySet()) {

			List<CurrencyExchange> usdRates = new ArrayList<CurrencyExchange>(
					rates.get(key));

			String currencyA = usdRates.get(0).getTitle();
			String currencyB = usdRates.get(1).getTitle();

			if (key.equals("USD")) {
				assertEquals("EUR", currencyB);
				assertEquals("GBP", currencyA);
			}

			if (key.equals("EUR")) {
				assertEquals("USD", currencyB);
				assertEquals("GBP", currencyA);
			}

			if (key.equals("GBP")) {
				assertEquals("EUR", currencyB);
				assertEquals("USD", currencyA);

			}
		}

		verify(currencyExchangeRepo).findAllCurencyExchanges();
	}

	public static Set<CurrencyExchange> currencyData() {
		CurrencyExchange usd = new CurrencyExchange();
		usd.setTitle("USD");
		usd.setKey("a");

		CurrencyExchange gbp = new CurrencyExchange();
		gbp.setTitle("GBP");
		gbp.setKey("a");

		CurrencyExchange eur = new CurrencyExchange();
		eur.setTitle("EUR");
		eur.setKey("a");

		Set<CurrencyExchange> currencies = new HashSet<CurrencyExchange>();
		currencies.addAll(Arrays.asList(usd, gbp, eur));

		return currencies;
	}
}
