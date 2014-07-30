package org.apilytic.currency.api;

import org.apilytic.currency.api.model.ExchangeCurrency;
import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.FinancialProvider;
import org.apilytic.currency.ingestion.rate.query.QueryRateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Query currency exchange query directly from query provider.
 * This is slow operation.
 *
 * <p>Supported exchange query providers:</p>
 * <ul>
 * <li>Yahoo finance</li>
 * <li>Google currency calculator</li>
 * </ul>
 *
 * Created by g on 7/11/14.
 */
@Service
public class DirectDealExchangeApi implements CurrencyExchangeApi {

    @Autowired
    private FinancialProvider financialProvider;

    @Autowired
    private QueryRateBuilder queryRateBuilder;

    @Autowired
    private RateFormat rateFormat;

    @Override
    public ExchangeCurrency exchangeSingleCurrency(CurrencyRate currencyRate) {
        financialProvider.setExchangeQuery(queryRateBuilder.createQueryRate(currencyRate.getFromCurrency(),
                currencyRate.getToCurrency()));
        List<? extends ExchangeRate> providedRates = financialProvider
                .provideRate();

        String rawRatio = providedRates.get(0).rate();

        String amount = rateFormat.cleanNumber(currencyRate.getAmount());
        BigDecimal ratio = new BigDecimal(rawRatio);
        BigDecimal convertedAmount = ratio.multiply(new BigDecimal(amount));

        String exchange = convertedAmount.setScale(2, RoundingMode.HALF_EVEN).toString();

        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        
        if(currencyRate.getLocale().isEmpty() == false) {
        	NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        	exchange = currencyFormat.format(exchange);
        }
        exchangeCurrency.setExchange(exchange);

        return exchangeCurrency;
    }

    @Override
    public List<ExchangeCurrency> exchangeMultipleCurrencies(List<CurrencyRate> currencyRates) {
        return currencyRates.stream().map(this::exchangeSingleCurrency).collect(Collectors.toList());
    }
}
