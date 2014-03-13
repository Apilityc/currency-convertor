package org.apilytc.currency.ingestion.vapor;

/**
 * Import ISO currency codes.
 * 
 * @author Georgi Lambov
 * 
 */
public interface VaporIngestion {

	/**
	 * Imports currency ISO codes.
	 */
	public void sync();
}
