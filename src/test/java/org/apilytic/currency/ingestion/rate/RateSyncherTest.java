package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RateSyncherTest {

	@InjectMocks
	private RateSyncher syncher;

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

	@Test
	public void sync() {
		Iterable<Currency> i = Arrays.asList(currency);
		String usd = "USD";

		when(currencyRepo.findAll()).thenReturn(i);
		when(currency.getCodes()).thenReturn(new HashSet<>(Arrays.asList(usd)));

		syncher.sync();

		assertTrue(syncher instanceof RateSyncher);
		verify(exchangeRepo).save(Arrays.asList(exchange));
	}
}