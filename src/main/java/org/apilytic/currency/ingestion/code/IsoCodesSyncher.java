package org.apilytic.currency.ingestion.code;

import io.reactivex.Observable;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class IsoCodesSyncher {

	@Autowired
	private IsoCodesFetcher fetcher;
	@Autowired
	private CurrencyRepository repo;
	@Autowired
	private Currency currency;

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

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
}
