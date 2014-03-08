package org.apilytc.domain;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.roo.addon.test.RooIntegrationTest;

//XXX Excluded from test suite
@RooIntegrationTest(entity = Currency.class, transactional = false)
public class CurrencyIntegrationTest {

	@Before
	public void setup() {

	}

	@Autowired
	private RedisTemplate<String, String> template;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOpts;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOpts;

	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOpts;

	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testRedisAddLink() {
		template.getConnectionFactory().getConnection().flushDb();
		valueOpts.set("link", "apilytic.org");
		assertEquals("apilytic.org", valueOpts.get("link"));
	}
}
