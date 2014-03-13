package org.apilytc.currency.ingestion.vapor;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.apilytc.currency.persistence.domain.CurrencyExchange;
import org.apilytc.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.SetOperations;

public class ISOCodesFromISOdotComSyncerTest {

	@Autowired
	private ISOCodesFromISOdotComSyncer syncer;

	@Autowired
	private FetchISOCodes fetchISOCodes;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	// TODO create unit test and remove integration test from here
	@Test
	public void test() {

		Integer initialSize = fetchISOCodes.fetch().getCurrencyTable()
				.getCurrencyCodes().size();

		syncer.sync();

		Long actualSize = setOps.size(CurrencyExchange.KEY);

		assertEquals(initialSize, actualSize);
	}
}
