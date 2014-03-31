package org.apilytic.currency.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.apilytic.currency.persistence.repository.RateTimeTrackRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = RateTimeTrack.class, transactional = false)
public class RateTimeTrackIntegrationTest {

	@Autowired
	private RateTimeTrackDataOnDemand dod;

	@Autowired
	private RateTimeTrackRepository rateTimeTrackRepo;

	@Autowired
	private RedisTemplate<String, RateTimeTrack> template;

	private boolean runOnce = false;

	@Before
	public void init() {
		if (runOnce) {
			return;
		}

		runOnce = true;
		// template.getConnectionFactory().getConnection().flushDb();
	}

	@Before
	public void initMethod() {
		template.setValueSerializer(new JacksonJsonRedisSerializer<RateTimeTrack>(
				RateTimeTrack.class));
	}

	@Test
	public void testSave() {
		assertNotNull(
				"Data on demand for 'Rate' failed to initialize correctly",
				dod.getRandomRateTimeTrack());

		RateTimeTrack obj = dod.getNewTransientRateTimeTrack(Integer.MAX_VALUE);
		Assert.assertNotNull(
				"Data on demand for 'Rate' failed to provide a new transient entity",
				obj);

		RateTimeTrack actual = rateTimeTrackRepo.save(obj);

		assertEquals(obj.getKey(), actual.getKey());
	}

	@Test
	public void testFindOne() {
		RateTimeTrack obj = dod.getRandomRateTimeTrack();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to initialize correctly",
				obj);
		String key = obj.getKey();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to provide an identifier",
				key);
		obj = rateTimeTrackRepo.findOne(key);
		Assert.assertNotNull(
				"Find method for 'RateTimeTrack' illegally returned null for id '"
						+ key + "'", obj);
		Assert.assertEquals(
				"Find method for 'RateTimeTrack' returned the incorrect identifier",
				key, obj.getKey());
	}

	@Test
	public void testDeleteByKey() {
		RateTimeTrack obj = dod.getRandomRateTimeTrack();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to initialize correctly",
				obj);
		String key = obj.getKey();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to provide an identifier",
				key);
		obj = rateTimeTrackRepo.findOne(key);
		rateTimeTrackRepo.delete(obj.getKey());
		Assert.assertNull("Failed to remove 'RateTimeTrack' with identifier '"
				+ key + "'", rateTimeTrackRepo.findOne(key));
	}

	@Test
	public void testDeleteByEntity() {
		RateTimeTrack obj = dod.getRandomRateTimeTrack();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to initialize correctly",
				obj);
		String key = obj.getKey();
		Assert.assertNotNull(
				"Data on demand for 'RateTimeTrack' failed to provide an identifier",
				key);
		obj = rateTimeTrackRepo.findOne(key);
		rateTimeTrackRepo.delete(obj);
		Assert.assertNull("Failed to remove 'RateTimeTrack' with identifier '"
				+ key + "'", rateTimeTrackRepo.findOne(key));
	}

}
