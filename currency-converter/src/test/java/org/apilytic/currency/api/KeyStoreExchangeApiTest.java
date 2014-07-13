package org.apilytic.currency.api;

import org.apilytic.currency.persistence.repository.RateRepository;
import org.mockito.Mock;
import org.testng.annotations.BeforeClass;

public class KeyStoreExchangeApiTest extends CurrencyExchangeApiTest {

    @Mock
    private RateRepository rateRepo;

    @BeforeClass
    public void init() {
        exchangeApi = new KeyStoreExchangeApi();
    }

}