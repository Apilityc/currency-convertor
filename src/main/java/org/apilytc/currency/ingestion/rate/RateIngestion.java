package org.apilytc.currency.ingestion.rate;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public interface RateIngestion {

	/**
	 * Sync currency rates from different providers.
	 */
	public void sync();
}
