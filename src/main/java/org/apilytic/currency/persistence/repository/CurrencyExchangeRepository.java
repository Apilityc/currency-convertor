package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface CurrencyExchangeRepository extends CrudRepository<CurrencyExchange, String> {

	Set<CurrencyExchange> findAllCurrencyExchanges();
}
