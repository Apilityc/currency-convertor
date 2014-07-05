package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;

import java.util.List;

/**
 * Currency converter operations to work with exchange rates.
 * <p>
 * Created by g on 7/5/14.
 */
public interface CurrencyExchangeApi {

    /**
     * @param exchangeRate
     * @return
     */
    CurrencyRate exchangeSingleRate(ExchangeRate exchangeRate);

    /**
     * @param exchangeRates
     * @return
     */
    CurrencyRate exchangeMultipleRates(List<ExchangeRate> exchangeRates);

    /**
     * @param exchangeRate
     * @return
     */
    CurrencyRate exchangeSingleRateWithoutCache(ExchangeRate exchangeRate);

    /**
     * @param exchangeRate
     * @return
     */
    CurrencyRate exchangeMultipleRatesWithoutCache(ExchangeRate exchangeRate);
}
