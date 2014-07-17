package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

/**
 * Created by g on 7/16/14.
 */
aspect DirectDealExchangeApiTestMock {

    @Mock
    RateRepository KeyStoreExchangeApiTest.rateRepo;

    private Map<String, String> exchangeRate = spy(new HashMap<>());

    /**
     * Stubs mocked data in test abstraction {@link org.apilytic.currency.api
     * .KeyStoreExchangeApiTest#exchangeSingleRate(org.apilytic.currency.api.model.ExchangeRate)}.
     */
    before(DirectDealExchangeApiTest p, ExchangeRate rate): target(p) && args(rate) && execution(void
            exchangeSingleRate(ExchangeRate)) {

        doReturn("0.73").when(exchangeRate).get(rate.getToCurrency());
    }
}
