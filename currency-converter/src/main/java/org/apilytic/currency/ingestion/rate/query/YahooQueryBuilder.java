package org.apilytic.currency.ingestion.rate.query;

import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.springframework.stereotype.Service;

/**
 * Created by g on 7/17/14.
 */
@Service
public class YahooQueryBuilder implements QueryRateBuilder {

    @Override
    public String createQueryRate(QueryRate rate) {
        //TODO finish implemenration
        String.format(YahooQueryRateBuilder.queryRatePattern, "", "");

        return String.format("");
    }
}
