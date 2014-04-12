package org.apilytic.currency.ingestion.vapor;

import org.apilytic.currency.ingestion.vapor.model.ISO4217Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class FetchISOCodesIntegrationTest {

	@Autowired
	private FetchISOCodes fetchISOCodes;

	// TODO move as integration test
	@Test
	public void testFetch() {
		ISO4217Bean isoCodes = fetchISOCodes.fetch();

		assertNotNull(isoCodes);
		assertNotNull(isoCodes.getCurrencyTable());
		assertTrue(isoCodes.getCurrencyTable().getCurrencyCodes().size() > 1);

		System.out.println(isoCodes.getCurrencyTable().getCurrencyCodes()
				.size());
	}
}
