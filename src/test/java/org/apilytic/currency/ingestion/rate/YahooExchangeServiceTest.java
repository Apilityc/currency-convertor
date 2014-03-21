package org.apilytic.currency.ingestion.rate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooCSVBean;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
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

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		rateIngestion = new YahooExchangeService();

		ReflectionTestUtils.setField(rateIngestion, "yahooFinanaceManager",
				yahooFinanaceManager);

		ReflectionTestUtils.setField(rateIngestion, "queryRateBuilder",
				queryRateBuilder);
		ReflectionTestUtils.setField(rateIngestion, "rateRepo", rateRepo);

	}

	@Test
	public void provideRate() {
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

		assertEquals(expectedUsdGbp.fromCurrency(), usdGbp.getKey());
		assertEquals(
				new HashSet<String>(Arrays.asList(expectedUsdGbp.toCurrency())),
				usdGbp.getValue().keySet());
		assertEquals(expectedUsdGbp.rate(), usdGbp.getValue().values()
				.iterator().next());

		assertEquals(expectedUsdEur.fromCurrency(), usdEur.getKey());
		assertEquals(
				new HashSet<String>(Arrays.asList(expectedUsdEur.toCurrency())),
				usdEur.getValue().keySet());
		assertEquals(expectedUsdEur.rate(), usdEur.getValue().values()
				.iterator().next());

		verify(yahooFinanaceManager).provideRate();
		verify(queryRateBuilder).createQueryRate();
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
}
