package org.apilytic.currency.ingestion.rate;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooCSVBean;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
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
	private YahooFinanceManager financialProvider;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		rateIngestion = new YahooExchangeService();

		ReflectionTestUtils.setField(rateIngestion, "financialProvider",
				financialProvider);
	}

	@Test
	public void provideRate() {
		doReturn(dataProviderForRates()).when(financialProvider).provideRate();

		rateIngestion.sync();

		verify(financialProvider).provideRate();
	}

	private List<? extends ExchangeRate> dataProviderForRates() {
		// TODO Auto-generated method stub
		return Arrays.asList(new YahooCSVBean());
	}
}
