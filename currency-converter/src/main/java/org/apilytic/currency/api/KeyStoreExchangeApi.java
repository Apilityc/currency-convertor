package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public ExchangeCurrency exchangeSingleRate(CurrencyRate currencyRate) {
        Rate rate = rateRepo.findOne(Rate.key(currencyRate.getFromCurrency()));

        String rawRatio = rate.getValue().get(currencyRate.getToCurrency());

        BigDecimal ratio = new BigDecimal(rawRatio);
        BigDecimal convertedAmount = ratio.multiply(new BigDecimal(currencyRate.getAmount()));

        String exchange = convertedAmount.setScale(2, RoundingMode.HALF_EVEN).toString();

        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        exchangeCurrency.setExchange(exchange);
        return exchangeCurrency;
    }

    @Override
    public List<ExchangeCurrency> exchangeMultipleRates(List<CurrencyRate> currencyRates) {
        return currencyRates.stream().map(this::exchangeSingleRate).collect(Collectors.toList());
    }
}
