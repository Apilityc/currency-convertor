package org.apilytc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.PostConstruct;

import org.apilytc.repository.RateRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Rate.class, transactional = false)
public class RateIntegrationTest {

	// template.getConnectionFactory().getConnection().flushDb();

	@Autowired
	private RedisTemplate<String, String> template;

	@Autowired
	private RateRepository rateRepo;

	@Autowired
	private RateDataOnDemand dod;

	@PostConstruct
	public void init() {
		template.getConnectionFactory().getConnection().flushDb();
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

	@Test
	public void testDelete() {

	}
}
