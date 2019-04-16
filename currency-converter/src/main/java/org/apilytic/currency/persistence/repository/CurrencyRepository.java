package org.apilytic.currency.persistence.repository;

import org.apilytic.currency.persistence.domain.Currency;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, String> {
}
