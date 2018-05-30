package org.apilytic.currency.exchange;

import org.apilytic.currency.persistence.domain.CurrencyPair;

/**
 * Internal API for exchange rate based on @see {@link CurrencyPair}
 */
public interface RateExchange {

	/**
	 * @param pair
	 * @return
	 */
	String exchange(CurrencyPair pair);

}
