package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

/**
 * @author g
 */
public class CurrencyExchangeApiTest {

    @Mock
    private CurrencyExchangeApi exchangeApi;

    @BeforeMethod
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testExchangeSingleRate() {
        ExchangeRate rate = new ExchangeRate();

        CurrencyRate currencyRate = exchangeApi.exchangeSingleRate(rate);

        fail();
    }

    @Test
    public void testExchangeMultipleRates() {
        fail();
    }

    @Test
    public void testExchangeSingleRateWithoutCache() {
        fail();
    }

    @Test
    public void testExchangeMultipleRatesWithoutCache() {
        fail();
    }
}