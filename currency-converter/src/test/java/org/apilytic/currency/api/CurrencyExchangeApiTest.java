package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.api.model.CurrencyRate;
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
    public void exchangeSingleCurrency(CurrencyRate rate) {
        ExchangeCurrency exchangeCurrency = exchangeApi.exchangeSingleCurrency(rate);
        assertEquals("9.00", exchangeCurrency.getExchange());
    }

    @Test(dataProvider = "exchangeRates")
    public void exchangeMultipleCurrencies(CurrencyRate currencyRate, CurrencyRate rate) {
        List<ExchangeCurrency> exchangeCurrencies = exchangeApi.exchangeMultipleCurrencies(Arrays.asList
                (currencyRate, rate));
        assertEquals("9.00", exchangeCurrencies.get(0).getExchange());
        assertEquals("30.49", exchangeCurrencies.get(1).getExchange());
    }
}