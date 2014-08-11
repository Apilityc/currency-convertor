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

    static CurrencyRate usd() {
        CurrencyRate usd = new CurrencyRate();
        usd.setAmount("12.33");
        usd.setFromCurrency("USD");
        usd.setToCurrency("EUR");

        return usd;
    }

    static CurrencyRate eur() {
        CurrencyRate rate = new CurrencyRate();
        rate.setAmount("55.44");
        rate.setFromCurrency("USD");
        rate.setToCurrency("EUR");

        return rate;
    }

    @DataProvider
    public Object[][] CurrencyExchangeApiTest.exchangeRates(Method method) {

        if (method.getName().equals("exchangeSingleCurrency")) {
            return new Object[][]{{usd()}};
        }

        return new Object[][]{{usd(), eur()}};
    }

    @DataProvider
    public Object[][] CurrencyExchangeApiTest.exchangeLocalRates(Method method) {
        CurrencyRate usd = usd();
        usd.setLocale("en_US");

        if (method.getName().equals("exchangeSingleCurrencyWithLocal")) {
            return new Object[][]{{usd}};
        }

        CurrencyRate eur = eur();
        eur.setLocale("en_US");
        return new Object[][]{{usd, eur}};
    }

}
