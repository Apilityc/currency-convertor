package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

/**
 * Created by g on 7/16/14.
 */
privileged aspect DirectDealExchangeApiTestMock {

    private Map<String, String> exchangeRate = spy(new HashMap<>());

    @BeforeMethod
    public void DirectDealExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Stubs mocked data in test abstraction {@link org.apilytic.currency.api
     * .DirectDealExchangeApiTest#exchangeSingleRate(org.apilytic.currency.api.model.ExchangeRate)}.
     */
    before(DirectDealExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(ExchangeRate)) {

        doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }

    after(DirectDealExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(ExchangeRate)) {

        doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }
}
