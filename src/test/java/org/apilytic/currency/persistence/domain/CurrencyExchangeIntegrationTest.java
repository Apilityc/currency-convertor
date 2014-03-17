package org.apilytic.currency.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Assert;
import org.junit.Before;
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

	@Before
	public void init() {
		if (runOnce == true) {
			return;
		}
		// TODO proper database manage when integration test and continues
		// integration tests are running - database should be not modified with
		// flushdb
		// template.getConnectionFactory().getConnection().flushDb();

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
	public void testSaveWithArray() {
		assertNotNull(
				"Data on demand for 'CurrencyExchange failed to initialize correctly",
				dod.getRandomCurrencyExchange());

		CurrencyExchange obj1 = dod.getNewTransientCurrencyExchange(11);
		Assert.assertNotNull(
				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
				obj1);

		CurrencyExchange obj2 = dod.getNewTransientCurrencyExchange(12);
		Assert.assertNotNull(
				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
				obj2);

		List<CurrencyExchange> asList = Arrays.asList(obj1, obj2);
		Iterable<CurrencyExchange> actual = currencyExchangeRepo.save(asList);

		int i = 0;
		for (CurrencyExchange entity : actual) {
			assertEquals(asList.get(i).getKey(), entity.getKey());
			assertEquals(asList.get(i).getTitle(), entity.getTitle());
			++i;
		}
	}

	@Test
	public void testFindOne() {
		CurrencyExchange obj = dod.getRandomCurrencyExchange();
		assertNotNull(
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
