package org.apilytc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.PostConstruct;

import org.apilytc.repository.RateTimeTrackRepository;
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

	@PostConstruct
	public void init() {
		template.getConnectionFactory().getConnection().flushDb();
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

}
