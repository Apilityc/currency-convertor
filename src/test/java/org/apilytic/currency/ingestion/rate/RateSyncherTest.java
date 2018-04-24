package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.IngestionSync;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class RateSyncherTest {

	@Test
	public void sync() {
		IngestionSync syncher = new RateSyncher();
	
		assertTrue(syncher instanceof IngestionSync);
	}
}