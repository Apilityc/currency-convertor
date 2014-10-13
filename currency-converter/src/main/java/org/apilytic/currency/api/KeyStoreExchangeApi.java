package org.apilytic.currency.api;

import java.util.List;
import java.util.stream.Collectors;

import org.apilytic.currency.api.calc.ExchangeCalculator;
import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Query currency exchange query from stored rates updated periodically.
 * This is fast operation.
 *
 * <ul>
 * <li>Yahoo finance</li>
 * <li>Google finance calculator</li>
 * </ul>
 *
 * Created by g on 7/11/14.
 */
@Service
public class KeyStoreExchangeApi implements CurrencyExchangeApi {

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private RateFormat rateFormat;

    @Override
    public ExchangeCurrency exchangeSingleCurrency(CurrencyRate currencyRate) {
        Rate rate = rateRepo.findOne(Rate.key(currencyRate.getFromCurrency()));

        String rawRatio = rate.getValue().get(currencyRate.getToCurrency());

        ExchangeCalculator exchangeCalc = new ExchangeCalculator();
        return exchangeCalc.calc(currencyRate, rawRatio);
    }

    @Override
    public List<ExchangeCurrency> exchangeMultipleCurrencies(List<CurrencyRate> currencyRates) {
        return currencyRates.stream().map(this::exchangeSingleCurrency).collect(Collectors.toList());
    }
}
