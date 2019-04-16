package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.ISO4217;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IsoCodesFetcherIntegrationTest {

	@Autowired
	private IsoCodesFetcher fetcher;

	@Test
	public void fetch() {
		ISO4217 iso = fetcher.fetch();

		assertNotNull(iso);
	}
}