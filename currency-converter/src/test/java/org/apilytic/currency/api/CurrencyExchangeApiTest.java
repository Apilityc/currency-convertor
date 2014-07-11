package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

/**
 * @author g
 */
public abstract class CurrencyExchangeApiTest {

    protected CurrencyExchangeApi exchangeApi;

    private ExchangeRate exchangeRate;

    @BeforeMethod
    public void initMethod() {
        MockitoAnnotations.initMocks(this);
        exchangeRate = new ExchangeRate();
    }

    @Test
    public void testExchangeSingleRate() {
        exchangeRate.setAmount("12.33");
        exchangeRate.setFromCurrency("USD");
        exchangeRate.setToCurrency("EUR");

        CurrencyRate currencyRate = exchangeApi.exchangeSingleRate(exchangeRate);
        assertEquals("12.33", currencyRate.getExchange());
    }

    @Test
    public void testExchangeMultipleRates() {
        exchangeRate.setAmount("12.33");
        exchangeRate.setFromCurrency("USD");
        exchangeRate.setToCurrency("EUR");

        ExchangeRate rate = new ExchangeRate();
        rate.setAmount("55.44");
        rate.setFromCurrency("USD");
        rate.setToCurrency("EUR");

        List<CurrencyRate> currencyRates = exchangeApi.exchangeMultipleRates(Arrays.asList(exchangeRate, rate));
        assertEquals("12.33", currencyRates.get(0).getExchange());
        assertEquals("55.44", currencyRates.get(1).getExchange());
    }
}