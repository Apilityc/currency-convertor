package org.apilytic.currency.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apilytic.currency.persistence.repository.RateRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Rate.class, transactional = false)
public class RateIntegrationTest {

	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private RateRepository rateRepo;

	@Autowired
	private RateDataOnDemand dod;

	private boolean runOnce = false;

	@Before
	public void init() {
		if (runOnce == true) {
			return;
		}

		// template.getConnectionFactory().getConnection().flushDb();
		runOnce = true;
	}

	@Test
	public void testSave() {
		assertNotNull(
				"Data on demand for 'Rate' failed to initialize correctly",
				dod.getRandomRate());

		Rate obj = dod.getNewTransientRate(Integer.MAX_VALUE);
		Assert.assertNotNull(
				"Data on demand for 'Rate' failed to provide a new transient entity",
				obj);

		Rate actual = rateRepo.save(obj);

		assertEquals(obj.getKey(), actual.getKey());
		assertEquals(obj.getValue().size(), actual.getValue().size());
	}

	@Test
	public void testFindOne() {
		Rate obj = dod.getRandomRate();
		Assert.assertNotNull(
				"Data on demand for 'Rate' failed to initialize correctly", obj);
		String key = obj.getKey();
		Assert.assertNotNull(
				"Data on demand for 'Rate' failed to provide an identifier",
				key);
		obj = rateRepo.findOne(key);
		Assert.assertNotNull(
				"Find method for 'Rate' illegally returned null for id '" + key
						+ "'", obj);
		Assert.assertEquals(
				"Find method for 'Rate' returned the incorrect identifier",
				key, obj.getKey());
	}

}
