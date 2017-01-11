package org.apilytic.currency.api;

import java.util.HashMap;

import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.testng.annotations.BeforeClass;

/**
 * @author g
 */
public class KeyStoreExchangeApiIT {

    private static final String EXCHANGE_API = "keyStoreExchangeApi";

    @Autowired
    @Qualifier(EXCHANGE_API)
    private CurrencyExchangeApi currencyExchangeApi;

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    @Qualifier(EXCHANGE_API)
    private CurrencyExchangeApi optionalCurrencyExchangeApi;

    @BeforeClass
    public void init() {
        Rate rate = new Rate();
        rate.setKey(Rate.key("EUR"));
        rate.setValue(new HashMap<String, String>() {
            {
                put("GBP", "5.45");
            }
        });
        rateRepository.save(rate);
    }

}
