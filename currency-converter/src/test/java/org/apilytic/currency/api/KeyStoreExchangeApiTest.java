package org.apilytic.currency.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author g
 */
@Test
public class KeyStoreExchangeApiTest extends CurrencyExchangeApiTest {

    @BeforeClass
    public void init() {
        exchangeApi = new KeyStoreExchangeApi();
    }

}