package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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

		DuckDuckGoChart fetcher = this.fetcher.fetch(pair);
		assertNotNull(fetcher);

		// retry to get result from server
		fetcher = retry(fetcher, 3);

		assertFalse(parser.parse(fetcher).isEmpty());
	}

	/**
	 *
	 * @param fetcher
	 * @param numberOfRetries
	 * @return
	 */
	private DuckDuckGoChart retry(DuckDuckGoChart fetcher, int numberOfRetries) {
		if (parser.parse(fetcher).isEmpty()) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			fetcher = this.fetcher.fetch(pair);
		}

		return fetcher;
	}
}