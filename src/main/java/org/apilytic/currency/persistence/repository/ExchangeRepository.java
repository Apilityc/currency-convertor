package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.Exchange;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface ExchangeRepository extends CrudRepository<Exchange, String> {
	Set<Exchange> findAllExchanges();
}
