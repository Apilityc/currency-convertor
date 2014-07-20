package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.FinancialProvider;
import org.apilytic.currency.ingestion.rate.query.QueryRateBuilder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.testng.annotations.BeforeMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by g on 7/16/14.
 */
privileged aspect DirectDealExchangeApiTestMock {

    @Mock
    FinancialProvider DirectDealExchangeApiTest.financialProvider;
    @Mock
    QueryRateBuilder DirectDealExchangeApiTest.queryRateBuilder;

    private Map<String, String> exchangeRate = spy(new HashMap<>());
    private List<? extends ExchangeRate> rates = spy(new ArrayList<>());

    @BeforeMethod
    public void DirectDealExchangeApiTest.initMethod() {
        MockitoAnnotations.initMocks(this);

        ReflectionTestUtils.setField(exchangeApi, "financialProvider", financialProvider);
        ReflectionTestUtils.setField(exchangeApi, "queryRateBuilder", queryRateBuilder);
    }


}
