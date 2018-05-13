package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class DuckDuckGoFetcherIntegrationTest {

	@Autowired
	private DuckDuckGoFetcher fetcher;

	@Test
	public void fetch() {
		DuckDuckGoChart fetch = fetcher.fetch("EURUSD");
		assertNotNull(fetch);
	}
}