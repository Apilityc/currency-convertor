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


}
