package org.apilytc.currency.persistence.repository;

import org.apilytc.currency.persistence.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends
		CrudRepository<CurrencyExchange, String> {
}
