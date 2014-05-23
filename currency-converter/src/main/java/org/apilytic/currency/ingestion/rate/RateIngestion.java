package org.apilytic.currency.ingestion.rate;

import org.springframework.stereotype.Service;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public interface RateIngestion {

	/**
	 * Sync currency rates from different providers.
	 * 
	 * @throws InterruptedException
	 */
	public void sync() throws InterruptedException;
}
