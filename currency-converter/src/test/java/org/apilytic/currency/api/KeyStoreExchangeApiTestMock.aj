package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by g on 7/13/14.
 */
privileged aspect KeyStoreExchangeApiTestMock {

    @Mock
    RateRepository KeyStoreExchangeApiTest.rateRepo;
    private Map<String, String> exchangeRate = Mockito.spy(new HashMap<>());

    /**
     * Stubs mocked data in test abstraction.
     */
    before(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate(ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());

        Rate rateEntity = Mockito.mock(Rate.class);

        Mockito.when(p.rateRepo.findOne(key)).thenReturn(rateEntity);
        Mockito.when(rateEntity.getValue()).thenReturn(exchangeRate);

        Mockito.doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }

    /**
     * Verify execution of the flow.
     */
    after(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate(ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());
        Mockito.verify(p.rateRepo).findOne(key);
        Mockito.verify(p.rateRepo.findOne(key)).getValue();
        Mockito.verify(exchangeRate).get(rate.getToCurrency());
    }

    @BeforeMethod
    public void KeyStoreExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(exchangeApi, "rateRepo", rateRepo);
    }

}
