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
import java.util.HashSet;

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

		YahooChart chart = mock(YahooChart.class);

		when(currencyRepo.findAll()).thenReturn(i);
		when(currency.getCodes()).thenReturn(new HashSet<>(Arrays.asList(usd, eur)));
		when(rateFetcher.fetch(pair)).thenReturn(chart);
		when(rateParser.parse(chart)).thenReturn("1.2");

		syncher.sync();

		verify(currencyRepo).findAll();
		verify(currency).getCodes();
		verify(pair).setFrom(usd);
		verify(pair).setTo(eur);
	}
}