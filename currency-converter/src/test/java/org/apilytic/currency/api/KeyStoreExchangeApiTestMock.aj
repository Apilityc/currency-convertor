package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;


/**
 * Created by g on 7/13/14.
 */
privileged aspect KeyStoreExchangeApiTestMock {

    /**
     * Stubs mocked data in test abstraction.
     */
    before(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate(ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());
        Mockito.when(p.rateRepo.findOne(key)).thenReturn(new Rate());
    }

    /**
     * Verify execution of the flow.
     */
    after(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate(ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());
        Mockito.verify(p.rateRepo).findOne(key);
    }

    @Mock
    RateRepository KeyStoreExchangeApiTest.rateRepo;

    @BeforeMethod
    public void KeyStoreExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(exchangeApi, "rateRepo", rateRepo);
    }

    @BeforeClass
    public void KeyStoreExchangeApiTest.init() {
        exchangeApi = new KeyStoreExchangeApi();
    }

}
