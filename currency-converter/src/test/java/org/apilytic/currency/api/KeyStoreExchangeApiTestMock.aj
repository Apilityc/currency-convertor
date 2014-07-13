package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.domain.Rate;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;


/**
 * Created by g on 7/13/14.
 */
public privileged aspect KeyStoreExchangeApiTestMock {

    /**
     * Stubbs mocked data in test abstraction.
     */
    before(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate(ExchangeRate)) {
        p.stubbing(rate);
    }

    /**
     *
     * @param exchangeRate
     */
    public void KeyStoreExchangeApiTest.stubbing(ExchangeRate exchangeRate) {
        String key = Rate.key(exchangeRate.getFromCurrency());
        Mockito.when(rateRepo.findOne(key)).thenReturn(new Rate());
    }

    @BeforeMethod
    public void KeyStoreExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(exchangeApi, "rateRepo", rateRepo);
    }

}
