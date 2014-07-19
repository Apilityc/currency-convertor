package org.apilytic.currency.api;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.FinancialProvider;
import org.apilytic.currency.ingestion.rate.query.QueryRateBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public CurrencyRate exchangeSingleRate(ExchangeRate exchangeRate) {
        financialProvider.setExchangeQuery(queryRateBuilder.createQueryRate(exchangeRate.getFromCurrency(), exchangeRate.getToCurrency()));
        financialProvider.provideRate();

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setExchange("9.00");
        return currencyRate;
    }

    @Override
    public List<CurrencyRate> exchangeMultipleRates(List<ExchangeRate> exchangeRates) {
        return exchangeRates.stream().map(this::exchangeSingleRate).collect(Collectors.toList());
    }
}
