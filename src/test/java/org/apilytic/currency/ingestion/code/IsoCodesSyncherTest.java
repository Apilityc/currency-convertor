package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.CurrencyEntry;
import org.apilytic.currency.persistence.domain.CurrencyTable;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.*;

public class IsoCodesSyncherTest {

	@Test
	public void sync() {
		IsoCodesSyncher syncher = new IsoCodesSyncher();
		IsoCodesFetcher fetcher = mock(IsoCodesFetcher.class);
		CurrencyRepository repo = mock(CurrencyRepository.class);

		ReflectionTestUtils.setField(syncher, "fetcher", fetcher);
		ReflectionTestUtils.setField(syncher, "repo", repo);

		CurrencyTable currencyTable = mock(CurrencyTable.class);
		ISO4217 iso = mock(ISO4217.class);
		CurrencyEntry currencyEntry = mock(CurrencyEntry.class);

		when(fetcher.fetch()).thenReturn(iso);
		when(iso.getCurrencyTable()).thenReturn(currencyTable);
		when(currencyEntry.getIsoCode()).thenReturn("eur");
		when(currencyTable.getCurrencyEntries()).thenReturn(new HashSet<>(Arrays.asList(currencyEntry)));

		Currency currency = mock(Currency.class);
		syncher.setCurrency(currency);
		syncher.sync();

		Set h = new HashSet<>(Arrays.asList("eur"));
		verify(currency).setCodes(h);
		verify(repo).save(currency);
	}
}