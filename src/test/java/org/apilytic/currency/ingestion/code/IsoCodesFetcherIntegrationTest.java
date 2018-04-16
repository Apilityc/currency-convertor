package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.ISO4217;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class IsoCodesFetcherIntegrationTest {

	@Autowired
	private IsoCodesFetcher fetcher;

	@Test
	public void fetch() {
		ISO4217 iso = fetcher.fetch();

		Assert.assertNotNull(iso);
	}
}