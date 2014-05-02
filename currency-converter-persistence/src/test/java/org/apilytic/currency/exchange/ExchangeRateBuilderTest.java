package org.apilytic.currency.exchange;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ExchangeRateBuilderTest {

	private ExchangeRateBuilder builder;

	@Mock
	private CurrencyExchangeRepository currencyExchangeRepo;

	@BeforeTest
	public void init() {
		builder = new ExchangeRateBuilder();

	}

	@BeforeTest
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(builder, "currencyExchangeRepo",
				currencyExchangeRepo);
	}

	@Test(dataProvider = "currencyData")
	public void testBuildOfExchangeRate(Set<CurrencyExchange> currencies) {
		when(currencyExchangeRepo.findAllCurencyExchanges()).thenReturn(
				currencies);

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

	@DataProvider
	public Object[][] currencyData() {
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

		return new Object[][] { { currencies } };
	}
}
