package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.parser.RateParser;
import org.apilytic.currency.persistence.domain.Currency;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.CurrencyRepository;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class RateSyncher {

	@Autowired
	private ExchangeRepository exchangeRepo;
	@Autowired
	private CurrencyRepository currencyRepo;
	@Autowired
	private RateFetch rateFetcher;
	@Autowired
	private RateParser rateParser;
	@Autowired
	private Exchange exchange;
	@Autowired
	private CurrencyPair pair;

	public void sync() {
		Stream<Currency> currencies = StreamSupport.stream(currencyRepo.findAll().spliterator(), false);


		Set<String> codes = new HashSet<>(Arrays.asList("USD", "BGN"));
		Map<String, String> defaultRates = codes.stream()
				.collect(Collectors.toMap(c -> c, c -> "0"));

		codes.stream().map(code -> {
			Map<String, String> rates = defaultRates.entrySet().stream()
					.filter(x -> !x.getKey().equals(code))
					.collect(Collectors.toMap(
							Map.Entry::getKey,
							rate -> {
								pair.setFrom(code);
								pair.setTo(rate.getKey());

								return rateParser.parse(rateFetcher.fetch(pair));
							}
					));

			exchange.setId(code);
			exchange.setRates(rates);

			exchangeRepo.save(exchange);

			return exchange;
		}).collect(Collectors.toList());
	}
}
