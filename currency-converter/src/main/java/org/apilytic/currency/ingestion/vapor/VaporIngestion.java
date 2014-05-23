package org.apilytic.currency.ingestion.vapor;

/**
 * Import ISO currency codes.
 * 
 * @author Georgi Lambov
 * 
 */
// TODO rename to something like ContryCurrencyIngestion
public interface VaporIngestion {

	/**
	 * Imports currency ISO codes.
	 */
	public void sync();
}
