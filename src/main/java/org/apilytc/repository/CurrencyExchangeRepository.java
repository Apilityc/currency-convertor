package org.apilytc.repository;

import org.apilytc.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeRepository extends
		CrudRepository<CurrencyExchange, String> {
}
