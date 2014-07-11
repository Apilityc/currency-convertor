package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Query currency exchange rate from stored rates updated periodically.
 * This is fast operation.
 *
 * <ul>
 * <li>Yahoo finance</li>
 * <li>Google finance calculator</li>
 * </ul>
 *
 * Created by g on 7/11/14.
 */
public class KeyStoreExchangeApi implements CurrencyExchangeApi {
    @Override
    public CurrencyRate exchangeSingleRate(ExchangeRate exchangeRate) {
        CurrencyRate rate = new CurrencyRate();
        rate.setExchange(exchangeRate.getAmount());
        return rate;
    }

    @Override
    public List<CurrencyRate> exchangeMultipleRates(List<ExchangeRate> exchangeRates) {
        return exchangeRates.stream().map(this::exchangeSingleRate).collect(Collectors.toList());
    }
}
