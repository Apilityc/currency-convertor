package org.apilytic.currency.ingestion.vapor;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

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

	@Test
	public void testFetch() {
		ISO4217Bean isoCodes = fetchISOCodes.fetch();

		assertNotNull(isoCodes);
		assertNotNull(isoCodes.getCurrencyTable());
		assertEquals(179, isoCodes.getCurrencyTable().getCurrencyCodes().size());

		System.out.println(isoCodes.getCurrencyTable().getCurrencyCodes()
				.size());
	}
}
