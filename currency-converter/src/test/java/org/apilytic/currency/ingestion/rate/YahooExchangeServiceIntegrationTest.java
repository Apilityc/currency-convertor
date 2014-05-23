package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.vapor.VaporIngestion;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

	@Autowired
	private VaporIngestion vaporIngestion;

	@BeforeClass
	public void init() {
		// template.getConnectionFactory().getConnection().flushDb();
	}

	@BeforeMethod
	public void initMethod() {
		// ReflectionTestUtils.setField(rateIngestion, "queryRateBuilder",
		// queryRateBuilder);
	}

	@Test(dataProvider = "queryRateProvider")
	public void testSyncInBatch(String queryRate) throws InterruptedException {
		// when(queryRateBuilder.createQueryRate()).thenReturn(queryRate);
		// vaporIngestion.sync();
		rateIngestion.sync();
		// rateRepository.findOne(Rate.key("USD"));
		// verify(queryRateBuilder).createQueryRate();

	}

}
