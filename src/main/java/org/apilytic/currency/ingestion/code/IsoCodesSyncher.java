package org.apilytic.currency.ingestion.code;

import io.reactivex.Observable;
import org.apilytic.currency.ingestion.IngestionSync;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import java.util.HashSet;
import java.util.Set;

public class IsoCodesSyncher implements IngestionSync {

	@Autowired
	private IsoCodesFetcher fetcher;
	@Autowired
	private CurrencyRepository repo;

	private Currency currency;

	@Override
	public void sync() {
		ISO4217 iso = fetcher.fetch();

		Set codes = new HashSet();

		/*
		iso.getCurrencyTable().getCurrencyEntries().stream()
				.map(o -> codes.add(o.getIsoCode()))
				.collect(Collectors.toSet());
		*/

		Observable.fromIterable(iso.getCurrencyTable().getCurrencyEntries())
				.flatMap(c -> Observable.just(c.getIsoCode()))
				.subscribe(c -> codes.add(c));

		currency.setCodes(codes);

		repo.save(currency);
	}

	@Required
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
