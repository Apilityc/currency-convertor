package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
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

    @Autowired
    private RateFormat rateFormat;

    @Override
    public ExchangeCurrency exchangeSingleCurrency(CurrencyRate currencyRate) {
        Rate rate = rateRepo.findOne(Rate.key(currencyRate.getFromCurrency()));

        String rawRatio = rate.getValue().get(currencyRate.getToCurrency());

        String amount = rateFormat.cleanNumber(currencyRate.getAmount());
        BigDecimal ratio = new BigDecimal(rawRatio);
        BigDecimal convertedAmount = ratio.multiply(new BigDecimal(amount));

        String exchange = convertedAmount.setScale(2, RoundingMode.HALF_EVEN).toString();

        //FIXME validation of the correct locale
        //FIXME pass client locale and remove hardcoded Locale.US
        // if format is not supports - do not format currency at all
        // cover with unit tests
        
        //TODO remove code duplicate with aspects.
        if(currencyRate.getLocale().isEmpty() == false) {
        	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        	exchange = currencyFormat.format(exchange);
        }
        
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        exchangeCurrency.setExchange(exchange);
        return exchangeCurrency;
    }

    @Override
    public List<ExchangeCurrency> exchangeMultipleCurrencies(List<CurrencyRate> currencyRates) {
        return currencyRates.stream().map(this::exchangeSingleCurrency).collect(Collectors.toList());
    }
}
