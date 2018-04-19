package org.apilytic.currency.ingestion.rate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class YahooFinanceFetcherIntegrationTest {

	@Autowired
	private YahooFinanceFetcher fetcher;

	@Test
	public void fetch() {
		fetcher.fetch();
	}
}