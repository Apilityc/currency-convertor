package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.aspectj.lang.JoinPoint;
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

    //TODO create with method name and if statement there in one aspect.

    /**
     * Stubs mocked data in test abstraction {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeSingleCurrency(org.apilytic.currency.api.model.ExchangeRate)}.
     */
    before(KeyStoreExchangeApiTest p, CurrencyRate rate): target(p) && args(rate) && execution(void
            exchangeSingleCurrency(CurrencyRate)) {
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
    after(KeyStoreExchangeApiTest p, CurrencyRate rate): target(p) && args(rate) && execution(void exchangeSingleCurrency
            (CurrencyRate)) {
        verifyExchangeSingleAndMultipleRates(p.rateRepo, 1, rate);
    }

    /**
     * Stubs mocked data in test abstraction in {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeMultipleCurrencies(org.apilytic.currency.api.model.ExchangeRate,
     * org.apilytic.currency.api.model.ExchangeRate)}
     */
    before(KeyStoreExchangeApiTest p, CurrencyRate rate, CurrencyRate rate1): target(p) && args(rate, rate1)
            && execution(void exchangeMultipleCurrencies(CurrencyRate, CurrencyRate)) {
        String key = Rate.key(rate.getFromCurrency());

        Rate rateEntity = mock(Rate.class);

        when(p.rateRepo.findOne(key)).thenReturn(rateEntity);
        when(rateEntity.getValue()).thenReturn(exchangeRate);

        doReturn("0.73").doReturn("0.55").when(exchangeRate).get(rate.getToCurrency());
    }

    /**
     * Verifies execution of the flow in {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeMultipleCurrencies(org.apilytic.currency.api.model.ExchangeRate,
     * org.apilytic.currency.api.model.ExchangeRate)}.
     */
    after(KeyStoreExchangeApiTest p, CurrencyRate rate, CurrencyRate rate1): target(p) && args(rate, rate1)
            && execution(void exchangeMultipleCurrencies(CurrencyRate, CurrencyRate)) {
        verifyExchangeSingleAndMultipleRates(p.rateRepo, 2, rate);
    }

    /**
     * Resets aspect mocks on every test run
     */
    @After("execution(* org.apilytic.currency.api.CurrencyExchangeApiTest.**(..))")
    public void resetAopMocks(JoinPoint joinPoint) {
        exchangeRate = spy(new HashMap<>());
    }

    //TODO create in aspect by method name in one if statement there.

    /**
     * Verifies execution of mocks.
     *
     * @param rateRepo
     * @param times
     * @param rate
     */
    private void verifyExchangeSingleAndMultipleRates(RateRepository rateRepo, int times, CurrencyRate rate) {
        String key = Rate.key(rate.getFromCurrency());
        verify(rateRepo, times(times)).findOne(key);
        verify(rateRepo.findOne(key), times(times)).getValue();
        verify(exchangeRate, times(times)).get(rate.getToCurrency());
    }
}
