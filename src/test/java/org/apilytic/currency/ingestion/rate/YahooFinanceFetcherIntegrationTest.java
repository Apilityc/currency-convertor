package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class YahooFinanceFetcherIntegrationTest {

	@Autowired
	private YahooFinanceFetcher fetcher;

	@Test
	public void fetch() {
		YahooChart fetch = fetcher.fetch();
		assertEquals(1, fetch.getResult().size());
	}
}