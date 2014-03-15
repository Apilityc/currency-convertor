package org.apilytic.currency.ingestion.vapor;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apilytic.currency.ingestion.vapor.FetchISOCodes;
import org.apilytic.currency.ingestion.vapor.ISOCodesFromISOdotComSyncer;
import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	@Test
	public void testSyncWithCorrectValues() {
		Integer initialSize = fetchISOCodes.fetch().getCurrencyTable()
				.getCurrencyCodes().size();

		syncer.sync();

		Long actualSize = setOps.size(CurrencyExchange.KEY);

		assertEquals(Long.valueOf(initialSize), actualSize);
	}
}
