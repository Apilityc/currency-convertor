package org.apilytic.currency.exchange;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.springframework.context.annotation.Primary;

/**
 * Internal API for exchange rate based on @see {@link CurrencyPair}
 */
@Primary
public interface RateExchange {

	/**
	 * @param pair
	 * @return
	 */
	String exchange(CurrencyPair pair);

}
