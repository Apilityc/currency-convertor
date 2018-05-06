package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.IngestionSync;
import org.apilytic.currency.ingestion.rate.parser.RateParser;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;

public class RateSyncherTest {

	private IngestionSync syncher;

	@Mock
	private ExchangeRepository exchangeRepo;
	@Mock
	private CurrencyRepository currencyRepo;
	@Mock
	private RateFetch rateFetcher;
	@Mock
	private RateParser rateParser;
	@Mock
	private Exchange exchange;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		syncher = new RateSyncher();

		ReflectionTestUtils.setField(syncher, "exchangeRepo", exchangeRepo);
		ReflectionTestUtils.setField(syncher, "currencyRepo", currencyRepo);
		ReflectionTestUtils.setField(syncher, "rateFetcher", rateFetcher);
		ReflectionTestUtils.setField(syncher, "rateParser", rateParser);
		ReflectionTestUtils.setField(syncher, "exchange", exchange);
	}


	@Test
	public void sync() {

		assertTrue(syncher instanceof IngestionSync);
	}
}