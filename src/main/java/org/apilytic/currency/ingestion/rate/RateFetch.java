package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.YahooChart;

public interface RateFetch {

	/**
	 * Fetches rates from Yahoo provier.
	 *
	 * @return
	 */
	YahooChart fetch();
}
