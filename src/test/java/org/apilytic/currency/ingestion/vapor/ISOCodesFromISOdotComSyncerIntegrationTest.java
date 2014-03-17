package org.apilytic.currency.ingestion.vapor;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class ISOCodesFromISOdotComSyncerIntegrationTest {

	@Autowired
	private ISOCodesFromISOdotComSyncer syncer;

	@Autowired
	private FetchISOCodes fetchISOCodes;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	@Autowired
	private RedisTemplate<String, CurrencyExchange> template;

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	private boolean runOnce = false;

	@Before
	public void init() {
		if (runOnce == true) {
			return;
		}

		template.getConnectionFactory().getConnection().flushDb();
		runOnce = true;
	}

	@Test
	public void testSyncWithCorrectValues() {
		Integer initialSize = fetchISOCodes.fetch().getCurrencyTable()
				.getCurrencyCodes().size();

		syncer.sync();

		Long actualSize = setOps.size(CurrencyExchange.KEY);

		assertEquals(Long.valueOf(initialSize), actualSize);
	}
}
