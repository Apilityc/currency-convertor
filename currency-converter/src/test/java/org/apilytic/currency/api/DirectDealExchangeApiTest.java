package org.apilytic.currency.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

/**
 * @author g
 */
@Test
public class DirectDealExchangeApiTest extends CurrencyExchangeApiTest {

    @BeforeClass
    public void init() {
        exchangeApi = new DirectDealExchangeApi();
    }

}