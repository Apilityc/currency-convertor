package org.apilytic.currency.ingestion.rate;

public interface RateFetch<T> {

	/**
	 * Fetches rates from Yahoo provier.
	 *
	 * @return
	 */
	T fetch(String currencyPair);
}
