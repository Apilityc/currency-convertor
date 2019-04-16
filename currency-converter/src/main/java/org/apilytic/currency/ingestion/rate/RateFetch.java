package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.CurrencyPair;

public interface RateFetch<T> {

	/**
	 * Fetches rates from currency exchange providers.
	 *
	 * @return
	 */
	T fetch(CurrencyPair pair);
}
