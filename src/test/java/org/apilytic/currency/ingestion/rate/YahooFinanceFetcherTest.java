package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.YahooChart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooFinanceFetcherTest {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private YahooChart.Holder holder;
	@Mock
	private CurrencyPair pair;

	private RateFetch rateFetch;
	private static final String URL = "https://yahoo?EURUSD=x";
	private static final String URL_JPY = "https://yahoo?JPYUSD=x";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		rateFetch = new YahooFinanceFetcher();

		ReflectionTestUtils.setField(rateFetch, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(rateFetch, "url", URL);
	}

	@Test
	public void fetchDefaultCurrency() {
		when(restTemplate.getForObject(URL, YahooChart.Holder.class)).thenReturn(holder);
		when(pair.getFrom()).thenReturn("EUR");
		when(pair.getTo()).thenReturn("USD");

		rateFetch.fetch(pair);

		verify(restTemplate).getForObject(URL, YahooChart.Holder.class);
		verify(pair).getFrom();
		verify(pair).getTo();
	}

	@Test
	public void fetch() {
		when(restTemplate.getForObject(URL_JPY, YahooChart.Holder.class)).thenReturn(holder);
		when(pair.getFrom()).thenReturn("JPY");
		when(pair.getTo()).thenReturn("USD");

		rateFetch.fetch(pair);

		verify(restTemplate).getForObject(URL_JPY, YahooChart.Holder.class);
		verify(pair).getFrom();
		verify(pair).getTo();
	}
}