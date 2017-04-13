package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

/**
 * Created by g on 4/13/17.
 */
public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, String> {

	/**
	 * @return
	 */
	Set<CurrencyExchange> findAllCurrencyExchanges();
}
