package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.CurrencyEntry;
import org.apilytic.currency.persistence.domain.CurrencyTable;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IsoCodesFetcherTest {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private CurrencyTable currencyTable;
	@Mock
	private CurrencyEntry currencyEntry;
	@Mock
	private ISO4217 iso;

	@InjectMocks
	private IsoCodesFetcher fetcher;

	@Test
	public void missingUri() {
		assertThrows(IllegalArgumentException.class, () -> fetcher.fetch());
	}

	@Test
	public void fetchCodes() {
		when(restTemplate.getForObject("https://uri", ISO4217.class)).thenReturn(iso);
		when(iso.getCurrencyTable()).thenReturn(currencyTable);
		when(currencyEntry.getIsoCode()).thenReturn("EUR");
		when(currencyTable.getCurrencyEntries()).thenReturn(new HashSet<>(Arrays.asList(currencyEntry)));

		fetcher.setIsoCodesUrl("https://uri");
		fetcher.fetch();

		verify(restTemplate).getForObject("https://uri", ISO4217.class);
		verify(currencyTable).setCurrencyEntries(new HashSet<>(Arrays.asList(currencyEntry)));
	}

	@Test
	public void fetchSkipInvalidCodes() {
		when(restTemplate.getForObject("https://uri", ISO4217.class)).thenReturn(iso);
		when(iso.getCurrencyTable()).thenReturn(currencyTable);
		when(currencyEntry.getIsoCode()).thenReturn(null, "");

		when(currencyTable.getCurrencyEntries()).thenReturn(new HashSet<>(Arrays.asList(currencyEntry,
				currencyEntry)));

		fetcher.setIsoCodesUrl("https://uri");
		fetcher.fetch();

		verify(restTemplate).getForObject("https://uri", ISO4217.class);
		verify(currencyTable).setCurrencyEntries(new HashSet<>());
	}
}