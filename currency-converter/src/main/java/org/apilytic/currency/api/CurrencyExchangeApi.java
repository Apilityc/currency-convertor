package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.api.model.CurrencyRate;

import java.util.List;

/**
 * Currency converter operations to work with exchange rates.
 * <p>
 * Created by g on 7/5/14.
 */
public interface CurrencyExchangeApi {

    /**
     * @param currencyRate
     * @return
     */
    ExchangeCurrency exchangeSingleCurrency(CurrencyRate currencyRate);

    /**
     * @param currencyRates
     * @return
     */
    List<ExchangeCurrency> exchangeMultipleCurrencies(List<CurrencyRate> currencyRates);
}
