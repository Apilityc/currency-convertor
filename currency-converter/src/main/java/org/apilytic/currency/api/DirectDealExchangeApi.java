package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;

import java.util.List;

/**
 * Query currency exchange rate directly from rate provider.
 * This is slow operation.
 *
 * <p>Supported exchange rate providers:</p>
 * <ul>
 * <li>Yahoo finance</li>
 * <li>Google currency calculator</li>
 * </ul>
 *
 * Created by g on 7/11/14.
 */
public class DirectDealExchangeApi implements CurrencyExchangeApi {
    @Override
    public CurrencyRate exchangeSingleRate(ExchangeRate exchangeRate) {
        return null;
    }

    @Override
    public List<CurrencyRate> exchangeMultipleRates(List<ExchangeRate> exchangeRates) {
        return null;
    }
}
