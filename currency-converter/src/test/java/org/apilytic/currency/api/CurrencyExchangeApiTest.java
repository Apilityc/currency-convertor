package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * @author g
 */
public abstract class CurrencyExchangeApiTest {

    protected CurrencyExchangeApi exchangeApi;

    @Test(dataProvider = "exchangeRates")
    public void exchangeSingleRate(ExchangeRate rate) {
        CurrencyRate currencyRate = exchangeApi.exchangeSingleRate(rate);
        assertEquals("9.00", currencyRate.getExchange());
    }

    @Test(dataProvider = "exchangeRates")
    public void exchangeMultipleRates(ExchangeRate exchangeRate, ExchangeRate rate) {
        List<CurrencyRate> currencyRates = exchangeApi.exchangeMultipleRates(Arrays.asList(exchangeRate, rate));
        assertEquals("12.33", currencyRates.get(0).getExchange());
        assertEquals("55.44", currencyRates.get(1).getExchange());
    }
}