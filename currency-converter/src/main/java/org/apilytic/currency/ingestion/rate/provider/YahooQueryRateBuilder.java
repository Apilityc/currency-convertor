package org.apilytic.currency.ingestion.rate.provider;

import org.apilytic.currency.exchange.ExchangeRateBuilder;
import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Create yahoo query query from exchange rates.
 *
 * @author Georgi Lambov
 */
@Service
public class YahooQueryRateBuilder {

    @Autowired
    private ExchangeRateBuilder exchangeRateBuilder;

    public static final String queryRatePattern = "&s=%s%s=X";

    /**
     * Builds yahoo query query builder
     *
     * @return
     */
    public StringBuilder createQueryRate() {
        exchangeRateBuilder.constructExchageRate();
        Map<String, Set<CurrencyExchange>> rates = exchangeRateBuilder
                .getExchangeRate().getRates();

        StringBuilder sb = new StringBuilder();

        String queryRate = rates.keySet().stream().map(fromCurrency -> {
            return rates.get(fromCurrency).stream().map(s -> String.format(queryRatePattern, fromCurrency, s.getTitle
                    ()))
                    .collect(Collectors.joining());
        }).collect(Collectors.joining());

        sb.append(queryRate);
        return sb;
    }
}
