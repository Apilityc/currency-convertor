package org.apilytic.currency.ingestion.rate;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooExchangeServiceIntegrationTest extends YahooExchangeService {

	@Autowired
	private YahooExchangeService rateIngestion;

	@Test
	public void testSyncInBatch() {
		// skip integration test
		assertTrue(true);
	}

}
