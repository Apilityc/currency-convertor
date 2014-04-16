package org.apilytic.currency.ingestion.rate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooExchangeServiceIntegrationTest {

	@Autowired
	private YahooExchangeService rateIngestion;

	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private RateRepository rateRepository;

	@Mock
	private YahooQueryRateBuilder queryRateBuilder;

	@BeforeClass
	public void init() {
		template.getConnectionFactory().getConnection().flushDb();
	}

	@BeforeMethod
	public void initMethod() {
		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(rateIngestion, "queryRateBuilder",
				queryRateBuilder);
	}

	@Test(dataProvider = "queryRateProvider")
	public void testSyncInBatch(String queryRate) throws InterruptedException {
		when(queryRateBuilder.createQueryRate()).thenReturn(queryRate);
		rateIngestion.sync();
		rateRepository.findOne(Rate.key("USD"));
		verify(queryRateBuilder).createQueryRate();
	}

}
