package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.domain.YahooChart;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class RateSyncherTest {


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
	@Mock
	private CurrencyPair pair;

	@InjectMocks
	private RateSyncher syncher;

	@Test
	public void sync() {
		Iterable<Currency> i = Arrays.asList(currency);
		String usd = "USD";
		String eur = "EUR";

		when(currencyRepo.findAll()).thenReturn(i);
		when(currency.getCodes()).thenReturn(new HashSet<>(Arrays.asList(usd, eur)));

		syncher.sync();

		verify(currencyRepo).findAll();
		verify(currency).getCodes();
	}
}