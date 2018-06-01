package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class DuckDuckGoFetcherIntegrationTest {

	@Autowired
	private DuckDuckGoFetcher fetcher;
	@Autowired
	private CurrencyPair pair;
	@Autowired
	@Qualifier("duckduckgo")
	private RateParser<DuckDuckGoChart> parser;

	@Test
	public void fetch() {
		pair.setTo("USD");
		pair.setFrom("EUR");

		DuckDuckGoChart fetch = fetcher.fetch(pair);
		assertNotNull(fetch);
		assertFalse(parser.parse(fetch).isEmpty());
	}
}