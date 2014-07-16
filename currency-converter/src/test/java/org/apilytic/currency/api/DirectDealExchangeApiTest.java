package org.apilytic.currency.api;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author g
 */
public class DirectDealExchangeApiTest extends CurrencyExchangeApiTest {

    @BeforeClass
    public void init() {
        exchangeApi = new DirectDealExchangeApi();
    }

}