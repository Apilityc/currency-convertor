package org.apilytic.currency.api;

import org.testng.annotations.BeforeClass;

/**
 * @author g
 */
public class KeyStoreExchangeApiTest extends CurrencyExchangeApiTest {

    @BeforeClass
    public void init() {
        exchangeApi = new KeyStoreExchangeApi();
    }

}