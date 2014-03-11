package org.apilytc.currency.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apilytc.currency.persistence.domain.CurrencyExchange;
import org.apilytc.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = CurrencyExchange.class, transactional = false)
public class CurrencyExchangeIntegrationTest {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	@Autowired
	private RedisTemplate<String, CurrencyExchange> template;
	@Autowired
	private CurrencyExchangeDataOnDemand dod;
	private boolean runOnce = false;

	@After
	public void init() {
		if (runOnce == true) {
			return;
		}

		template.getConnectionFactory().getConnection().flushDb();
		runOnce = true;
	}

	@Test
	public void testSave() {
		assertNotNull(
				"Data on demand for 'CurrencyExchange failed to initialize correctly",
				dod.getRandomCurrencyExchange());

		CurrencyExchange obj = dod
				.getNewTransientCurrencyExchange(Integer.MAX_VALUE);
		Assert.assertNotNull(
				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
				obj);

		CurrencyExchange actual = currencyExchangeRepo.save(obj);

		assertEquals(obj.getKey(), actual.getKey());
		assertEquals(obj.getTitle(), actual.getTitle());
	}

	@Test
	public void testFindOne() {
		CurrencyExchange obj = dod.getRandomCurrencyExchange();
		Assert.assertNotNull(
				"Data on demand for 'CurrencyExchange failed to initialize correctly",
				obj);
		String key = obj.getKey();
		assertNotNull(
				"Data on demand for 'CurrencyExchange failed to provide an identifier",
				key);
		obj = currencyExchangeRepo.findOne(key);
		assertNotNull(
				"Find method for 'CurrencyExchange illegally returned null for id '"
						+ key + "'", obj);
		assertEquals(
				"Find method for 'CurrencyExchange returned the incorrect identifier",
				key, obj.getKey());
	}
}