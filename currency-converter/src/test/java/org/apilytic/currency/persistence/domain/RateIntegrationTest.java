package org.apilytic.currency.persistence.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
	public void testSaveMixedCurrencies() {
		Rate usdGbp = new Rate();
		usdGbp.setKey(Rate.key("USD"));
		Map<String, String> value = new HashMap<String, String>();
		value.put("GBP", "12.10");
		usdGbp.setValue(value);

		Rate eurGbp = new Rate();
		eurGbp.setKey(Rate.key("EUR"));
		value = new HashMap<String, String>();
		value.put("GBP", "11.23");
		eurGbp.setValue(value);

		Rate usdJpy = new Rate();
		usdJpy.setKey(Rate.key("USD"));
		value = new HashMap<String, String>();
		value.put("JPY", "1.44");
		usdJpy.setValue(value);

		rateRepo.save(Arrays.asList(usdGbp, eurGbp, usdJpy));

		Rate actualUsdGbpRate = rateRepo.findOne(Rate.key("USD"));
		assertEquals(2, actualUsdGbpRate.getValue().values().size());

		Rate actualEurRate = rateRepo.findOne(Rate.key("EUR"));
		assertEquals(1, actualEurRate.getValue().values().size());
	}
}
