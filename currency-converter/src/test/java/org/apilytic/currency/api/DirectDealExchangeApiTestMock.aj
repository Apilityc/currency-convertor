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

    /**
     * Stubs mocked data in test abstraction {@link org.apilytic.currency.api
     * .DirectDealExchangeApiTest#exchangeSingleRate(org.apilytic.currency.api.model.ExchangeRate)}.
     */
    before(DirectDealExchangeApiTest p, CurrencyRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(CurrencyRate)) {

        when(p.queryRateBuilder.createQueryRate("USD", "EUR")).thenReturn("USD_TO_EUR");
        doReturn(rates).when(p.financialProvider).provideRate();

        ExchangeRate mock = mock(org.apilytic.currency.ingestion.rate
                .provider.ExchangeRate.class);
        when(mock.rate()).thenReturn("0.73");
        doReturn(mock).when(rates).get(0);

        doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }

    after(DirectDealExchangeApiTest p, CurrencyRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(CurrencyRate)) {

        verify(p.queryRateBuilder).createQueryRate("USD", "EUR");
    }

    before(DirectDealExchangeApiTest p, CurrencyRate rate, CurrencyRate rate1): target(p) && args(rate,
    rate1) && execution(void
            exchangeMultipleRates(CurrencyRate, CurrencyRate)) {
        when(p.queryRateBuilder.createQueryRate("USD", "EUR")).thenReturn("USD_TO_EUR");
        doReturn(rates).when(p.financialProvider).provideRate();

        ExchangeRate mock = mock(org.apilytic.currency.ingestion.rate
                .provider.ExchangeRate.class);
        when(mock.rate()).thenReturn("0.73").thenReturn("0.55");
        doReturn(mock).when(rates).get(0);

        doReturn("0.73").doReturn("0.55").when(exchangeRate).get(rate.getToCurrency());
    }

    after(DirectDealExchangeApiTest p, CurrencyRate rate, CurrencyRate rate1): target(p) && args(rate,
    rate1) && execution(void
            exchangeMultipleRates(CurrencyRate, CurrencyRate)) {
        verify(p.queryRateBuilder, times(2)).createQueryRate("USD", "EUR");
    }
}
