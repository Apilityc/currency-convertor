package org.apilytc.domain;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.roo.addon.test.RooIntegrationTest;

@RooIntegrationTest(entity = Currency.class, transactional = false)
public class CurrencyIntegrationTest {

	@Before
	public void setup() {
		template.getConnectionFactory().getConnection().flushDb();
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
		valueOpts.set("link", "apilytic.org");
		assertEquals("apilytic.org", valueOpts.get("link"));
	}

	@Test
	public void testRateKeyStorate() {
		Map<String, String> exchangeRate = new HashMap<String, String>();
		exchangeRate.put("EUR", "1.24455");
		exchangeRate.put("GBP", "12.0023");

		Rate r = new Rate();
		r.setKey("USD");
		r.setValue(exchangeRate);

		hashOpts.putAll(r.getKey(), r.getValue());
	}
}
