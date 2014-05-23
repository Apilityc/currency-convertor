package org.apilytic.currency.persistence.repository;

import java.util.Set;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@Repository
public interface CurrencyExchangeRepository extends
		CrudRepository<CurrencyExchange, String> {

	/**
	 * Retrieves all currencyExchange as set.
	 * 
	 * @return
	 */
	Set<CurrencyExchange> findAllCurencyExchanges();

}
