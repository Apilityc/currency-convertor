package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.aspectj.lang.annotation.After;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


/**
 * Created by g on 7/13/14.
 */
privileged aspect KeyStoreExchangeApiTestMock {

    @Mock
    RateRepository KeyStoreExchangeApiTest.rateRepo;

    private Map<String, String> exchangeRate = spy(new HashMap<>());

    @BeforeMethod
    public void KeyStoreExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(exchangeApi, "rateRepo", rateRepo);
    }

    /**
     * Stubs mocked data in test abstraction {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeSingleRate(org.apilytic.currency.api.model.ExchangeRate)}.
     */
    before(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());

        Rate rateEntity = mock(Rate.class);

        when(p.rateRepo.findOne(key)).thenReturn(rateEntity);
        when(rateEntity.getValue()).thenReturn(exchangeRate);

        doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }

    /**
     * Verifies execution of the flow {@link org.apilytic.currency.api.KeyStoreExchangeApiTest#exchangeSingleRate(org
     * .apilytic.currency.api.model.ExchangeRate)}
     */
    after(KeyStoreExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void exchangeSingleRate
            (ExchangeRate)) {
        verifyExchangeSingleAndMultipleRates(p.rateRepo, 1, rate);
    }

    /**
     * Stubs mocked data in test abstraction in {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeMultipleRates(org.apilytic.currency.api.model.ExchangeRate,
     * org.apilytic.currency.api.model.ExchangeRate)}
     */
    before(KeyStoreExchangeApiTest p, ExchangeRate rate, ExchangeRate rate1): target(p) && args(rate, rate1)
            && execution(void exchangeMultipleRates(ExchangeRate, ExchangeRate)) {
        String key = Rate.key(rate.getFromCurrency());

        Rate rateEntity = mock(Rate.class);

        when(p.rateRepo.findOne(key)).thenReturn(rateEntity);
        when(rateEntity.getValue()).thenReturn(exchangeRate);

        doReturn("0.73").doReturn("0.55").when(exchangeRate).get(rate.getToCurrency());
    }

    /**
     * Verifies execution of the flow in {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeMultipleRates(org.apilytic.currency.api.model.ExchangeRate,
     * org.apilytic.currency.api.model.ExchangeRate)}.
     */
    after(KeyStoreExchangeApiTest p, ExchangeRate rate, ExchangeRate rate1): target(p) && args(rate, rate1)
            && execution(void exchangeMultipleRates(ExchangeRate, ExchangeRate)) {
        verifyExchangeSingleAndMultipleRates(p.rateRepo, 2, rate);
    }

    //TODO rewrite without annotation

    /**
     * Resets aspect mocks on every test run
     */
    @After("execution(* org.apilytic.currency.api.CurrencyExchangeApiTest.**(..))")
    public void resetAopMocks() {
        exchangeRate = spy(new HashMap<>());
    }

    /**
     * Verifies execution of mocks.
     *
     * @param rateRepo
     * @param times
     * @param rate
     */
    private void verifyExchangeSingleAndMultipleRates(RateRepository rateRepo, int times, ExchangeRate rate) {
        String key = Rate.key(rate.getFromCurrency());
        verify(rateRepo, times(times)).findOne(key);
        verify(rateRepo.findOne(key), times(times)).getValue();
        verify(exchangeRate, times(times)).get(rate.getToCurrency());
    }
}
