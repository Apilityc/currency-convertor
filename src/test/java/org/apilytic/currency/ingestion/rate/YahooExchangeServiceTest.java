package org.apilytic.currency.ingestion.rate;

import static org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder.queryRatePattern;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooCSVBean;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateParser;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooExchangeServiceTest {

	private RateIngestion rateIngestion;
	@Mock
	private YahooFinanceManager yahooFinanaceManager;
	@Mock
	private YahooQueryRateBuilder queryRateBuilder;
	@Mock
	private RateRepository rateRepo;
	@Mock
	private YahooQueryRateParser rateParser;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		rateIngestion = new YahooExchangeService();

		ReflectionTestUtils.setField(rateIngestion, "yahooFinanaceManager",
				yahooFinanaceManager);

		ReflectionTestUtils.setField(rateIngestion, "queryRateBuilder",
				queryRateBuilder);
		ReflectionTestUtils.setField(rateIngestion, "rateRepo", rateRepo);
		ReflectionTestUtils.setField(rateIngestion, "rateParser", rateParser);

	}

	@Test
	public void provideRate() {

		when(rateParser.splitInChunks())
				.thenReturn(dataProviderForRateParser());

		doReturn(dataProviderForRates()).when(yahooFinanaceManager)
				.provideRate();

		rateIngestion.sync();

		@SuppressWarnings("unchecked")
		List<Rate> rates = (List<Rate>) ReflectionTestUtils.getField(
				rateIngestion, "rates");

		Rate usdGbp = rates.get(0);
		Rate usdEur = rates.get(1);

		ExchangeRate expectedUsdGbp = dataProviderForRates().get(0);
		ExchangeRate expectedUsdEur = dataProviderForRates().get(1);

		assertEquals(Rate.key(expectedUsdGbp.fromCurrency()), usdGbp.getKey());
		assertEquals(
				new HashSet<String>(Arrays.asList(expectedUsdGbp.toCurrency())),
				usdGbp.getValue().keySet());
		assertEquals(expectedUsdGbp.rate(), usdGbp.getValue().values()
				.iterator().next());

		assertEquals(Rate.key(expectedUsdEur.fromCurrency()), usdEur.getKey());
		assertEquals(
				new HashSet<String>(Arrays.asList(expectedUsdEur.toCurrency())),
				usdEur.getValue().keySet());
		assertEquals(expectedUsdEur.rate(), usdEur.getValue().values()
				.iterator().next());

		verify(yahooFinanaceManager).provideRate();
		verify(queryRateBuilder).createQueryRate();
		verify(rateParser).splitInChunks();
	}

	private List<? extends ExchangeRate> dataProviderForRates() {

		YahooCSVBean usdGbp = new YahooCSVBean();
		usdGbp.setCurrency(String.format(
				YahooQueryRateBuilder.queryRatePattern, "USD", "GBP"));
		usdGbp.setRate("12.00");

		YahooCSVBean usdEur = new YahooCSVBean();
		usdEur.setCurrency(String.format(
				YahooQueryRateBuilder.queryRatePattern, "USD", "EUR"));
		usdEur.setRate("1.75");

		return Arrays.asList(usdGbp, usdEur);
	}

	private Set<String> dataProviderForRateParser() {
		String[] currencies = { "GBP", "EUR", "JPY" };

		String queryRate = "";
		for (String currency : currencies) {
			queryRate += String.format(queryRatePattern, "USD", currency);
		}

		Set<String> rates = new LinkedHashSet<String>();
		rates.add(queryRate);

		return rates;
	}
}
