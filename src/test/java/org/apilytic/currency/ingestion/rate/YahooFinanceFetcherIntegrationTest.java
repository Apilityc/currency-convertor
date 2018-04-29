package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class YahooFinanceFetcherIntegrationTest {

	@Autowired
	private RateFetch fetcher;

	@Test
	public void fetch() {
		YahooChart fetch = fetcher.fetch();
		assertEquals(1, fetch.getResult().size());
	}

	@Test
	public void retrieveRate() {
		YahooChart fetcher = this.fetcher.fetch();

		String rate = fetcher.getResult().get(0).getIndicators().getAdjclose().get(0)
				.getAdjclose().get(0);

		assertNotNull(rate);

		fetcher.getResult().stream()
				.findFirst().get().getIndicators().getAdjclose()
				.stream().findFirst().get()
				.getAdjclose().stream()
				.peek(s -> assertNotNull(s))
				.findFirst();


		fetcher.getResult().stream()
				.peek(s -> s.getIndicators().getAdjclose()
						.stream().peek(e -> e.getAdjclose().stream()
								.peek(i -> assertNotNull(i))
								.findFirst())
						.findFirst())
				.findFirst();
	}
}