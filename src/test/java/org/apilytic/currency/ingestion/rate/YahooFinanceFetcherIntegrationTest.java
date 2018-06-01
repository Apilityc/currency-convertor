package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.YahooChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class YahooFinanceFetcherIntegrationTest {

	@Autowired
	private RateFetch<YahooChart> fetcher;

	@Autowired
	private CurrencyPair pair;

	@BeforeEach
	public void init() {
		pair.setFrom("EUR");
		pair.setTo("USD");
	}

	@Test
	public void fetch() {
		YahooChart fetch = fetcher.fetch(pair);
		assertEquals(1, fetch.getResult().size());
	}

	@Test
	public void retrieveRate() {
		YahooChart fetcher = this.fetcher.fetch(pair);

		String rate = fetcher
				.getResult().get(0)
				.getIndicators().getAdjclose().get(0)
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