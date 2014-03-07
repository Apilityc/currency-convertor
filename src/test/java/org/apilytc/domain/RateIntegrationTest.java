package org.apilytc.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.HashMap;
import java.util.Map;

import org.apilytc.service.RateService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.configurable.RooConfigurable;

@RooConfigurable
public class RateIntegrationTest {

	@Autowired
	private RateService rateService;

	@Test
	public void testSave() {
		Rate rate = new Rate();
		rate.setKey(Rate.key("USD"));

		Map<String, String> rates = new HashMap<String, String>();
		rates.put("EUR", "1.24324");
		rates.put("GBP", "2.44435");

		rate.setValue(rates);

		Rate entry = rateService.save(rate);

		assertNotNull(entry);
		assertEquals(2, entry.getValue().size());
		assertEquals("rate:USD", entry.getKey());
	}

	@Test
	public void testFindOne() {
		Rate rate = rateService.findOne(Rate.key("USD"));
		assertNotNull(rate);
		assertEquals(2, rate.getValue().size());
		assertEquals("rate:USD", rate.getKey());
	}

	@Test
	public void testDelete() {

	}
}
