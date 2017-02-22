package org.apilytic.currency.ingestion.vapor;

import static org.testng.AssertJUnit.assertEquals;

import javax.annotation.Resource;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	@Test
	public void syncWithCorrectValues() {
		Integer initialSize = fetchISOCodes.fetch().getCurrencyTable()
				.getCurrencyCodes().size();

		syncer.sync();

  		Long actualSize = setOps.size(CurrencyExchange.key());

		assertEquals(Long.valueOf(initialSize), actualSize);
	}

	@BeforeClass
	// TODO move as before suite
	public void beforeSuite() {
		template.getConnectionFactory().getConnection().flushDb();
	}
}
