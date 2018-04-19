package org.apilytic.currency.ingestion.rate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertTrue;

public class YahooFinanceFetcherIntegrationTest {

	@Autowired
	private YahooFinanceFetcher fetcher;

	@Test
	public void fetch() {
		fetcher.fetch();
		assertTrue(true);
	}
}