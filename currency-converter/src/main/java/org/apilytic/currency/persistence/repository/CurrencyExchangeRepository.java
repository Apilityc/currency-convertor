package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * @author Georgi Lambov
 */
public interface CurrencyExchangeRepository extends
		CrudRepository<CurrencyExchange, String> {

	/**
	 * Retrieves all currencyExchange as set.
	 *
	 * @return
	 */
	Set<CurrencyExchange> findAllCurencyExchanges();

}
