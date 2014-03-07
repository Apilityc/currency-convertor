package org.apilytc.repository;

import java.util.List;

import org.apilytc.domain.Currency;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoRepository;

@RooMongoRepository(domainType = Currency.class)
public interface CurrencyRepository {

	List<Currency> findAll();
}
