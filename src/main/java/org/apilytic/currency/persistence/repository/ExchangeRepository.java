package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.Exchange;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRepository extends CrudRepository<Exchange, String> {
}
