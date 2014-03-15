package org.apilytic.currency.ingestion.vapor;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.apilytc.currency.ingestion.vapor.FetchISOCodes;
import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class FetchISOCodesTest {

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
