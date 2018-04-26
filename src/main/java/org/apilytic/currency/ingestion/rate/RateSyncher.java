package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.IngestionSync;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class RateSyncher implements IngestionSync {

	@Autowired
	private ExchangeRepository exchangeRepo;
	@Autowired
	private CurrencyRepository currencyRepo;

	@Override
	public void sync() {
		Stream<Currency> currencies = StreamSupport.stream(currencyRepo.findAll().spliterator(), false);

		currencies.peek(c -> {
			List<Exchange> exchanges = c.getCodes().stream()
					.map(code -> {
						Exchange e = new Exchange();
						e.setId(code);
						return e;
					})
					.collect(Collectors.toList());

			exchangeRepo.save(exchanges);
		}).findFirst();

	}
}
