package org.apilytic.currency.api;

import org.testng.annotations.BeforeClass;

import static org.testng.Assert.*;

public class KeyStoreExchangeApiTest extends CurrencyExchangeApiTest {

    @BeforeClass
    public void init() {
        exchangeApi = new KeyStoreExchangeApi();
    }

}