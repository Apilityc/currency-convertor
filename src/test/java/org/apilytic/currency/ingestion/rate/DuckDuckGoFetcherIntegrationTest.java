package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class DuckDuckGoFetcherIntegrationTest {

	@Autowired
	private DuckDuckGoFetcher fetcher;
	@Autowired
	private CurrencyPair pair;

	@Test
	public void fetch() {
		pair.setTo("USD");
		pair.setFrom("EUR");

		DuckDuckGoChart fetch = fetcher.fetch(pair);
		assertNotNull(fetch);
	}
}