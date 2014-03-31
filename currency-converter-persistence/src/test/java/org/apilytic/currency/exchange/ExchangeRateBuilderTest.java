package org.apilytic.currency.exchange;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
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
			if (key.equals("USD")) {
				for (CurrencyExchange c : rates.get("USD")) {
					assertTrue(c.getTitle().equals("EUR")
							|| c.getTitle().equals("GBP"));
				}
			}

			if (key.equals("EUR")) {
				for (CurrencyExchange c : rates.get("EUR")) {
					assertTrue(c.getTitle().equals("USD")
							|| c.getTitle().equals("GBP"));
				}
			}

			if (key.equals("GBP")) {
				for (CurrencyExchange c : rates.get("GBP")) {
					assertTrue(c.getTitle().equals("EUR")
							|| c.getTitle().equals("USD"));
				}
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
