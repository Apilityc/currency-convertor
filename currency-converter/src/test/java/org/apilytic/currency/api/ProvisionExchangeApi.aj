package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.testng.annotations.DataProvider;

import java.lang.reflect.Method;

/**
 * Provision test data.
 *
 * Created by g on 7/12/14.
 */
privileged aspect ProvisionExchangeApi {

    @DataProvider
    public Object[][] CurrencyExchangeApiTest.exchangeRates(Method method) {

        CurrencyRate usd = new CurrencyRate();
        usd.setAmount("12.33");
        usd.setFromCurrency("USD");
        usd.setToCurrency("EUR");

        CurrencyRate rate = new CurrencyRate();
        rate.setAmount("55.44");
        rate.setFromCurrency("USD");
        rate.setToCurrency("EUR");

        if (method.getName().equals("exchangeSingleRate")) {
            return new Object[][]{{usd}};
        }

        return new Object[][]{{usd, rate}};
    }

}
