package org.apilytc.domain;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Currency.class, transactional = false)
public class CurrencyIntegrationTest {

	@Autowired
	private RedisTemplate<String, String> template;

	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOpts;

	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOpts;

	@Test
	public void testMarkerMethod() {
	}

	@Test
	public void testRedisAddLink() {

		listOpts.leftPush("left", "right");
		valueOpts.set("sah", "bah");

	}
}
