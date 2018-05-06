package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.IngestionSync;
import org.apilytic.currency.ingestion.rate.parser.RateParser;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
	@Mock
	private Currency currency;

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
		Iterable<Currency> i = Arrays.asList(currency);
		String usd = "USD";

		when(currencyRepo.findAll()).thenReturn(i);
		when(currency.getCodes()).thenReturn(new HashSet<>(Arrays.asList(usd)));

		syncher.sync();

		assertTrue(syncher instanceof IngestionSync);
		verify(exchangeRepo).save(Arrays.asList(exchange));
	}
}