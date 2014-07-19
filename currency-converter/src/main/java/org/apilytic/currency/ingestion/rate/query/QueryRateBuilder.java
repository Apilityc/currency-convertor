package org.apilytic.currency.ingestion.rate.query;

/**
 * Created by g on 7/17/14.
 */
public interface QueryRateBuilder {

    /**
     * Builds query to retrieve exchange rate from rate provider service.
     *
     * @param fromCurrency currency from
     * @param toCurrency currency to
     * @return
     */
    String createQueryRate(String fromCurrency, String toCurrency);
}
