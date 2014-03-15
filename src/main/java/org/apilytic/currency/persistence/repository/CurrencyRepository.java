package org.apilytic.currency.persistence.repository;

import java.util.List;

import org.apilytic.currency.persistence.domain.Currency;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

@RooMongoRepository(domainType = Currency.class)
public interface CurrencyRepository {

	List<Currency> findAll();
}
