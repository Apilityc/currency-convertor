package org.apilytic.currency.ingestion.rate.query;

import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.springframework.stereotype.Service;

/**
 * Yahoo implementation to send query format to the server.
 *
 * Created by g on 7/17/14.
 */
@Service
public class YahooQueryBuilder implements QueryRateBuilder {

    @Override
    public String createQueryRate(String fromCurrency, String toCurrency) {
        return String.format(YahooQueryRateBuilder.queryRatePattern, fromCurrency, toCurrency);
    }
}
