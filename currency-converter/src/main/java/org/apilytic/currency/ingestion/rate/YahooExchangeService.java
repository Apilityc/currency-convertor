package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.rate.provider.ExchangeRate;
import org.apilytic.currency.ingestion.rate.provider.YahooFinanceManager;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder;
import org.apilytic.currency.ingestion.rate.provider.YahooQueryRateParser;
import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Sync currencies to database.
 *
 * @author Georgi Lambov
 */
@Service
public class YahooExchangeService implements RateIngestion {

    @Autowired
    private YahooFinanceManager yahooFinanaceManager;

    @Autowired
    private YahooQueryRateBuilder queryRateBuilder;

    @Autowired
    private RateRepository rateRepo;

    @Autowired
    private YahooQueryRateParser rateParser;

    /*
     * (non-Javadoc)
     *
     * @see org.apilytic.currency.ingestion.query.RateIngestion#sync()
     */
    @Override
    public void sync() throws InterruptedException {
        rateParser.setQueryRate(queryRateBuilder.createQueryRate());
        Set<String> rateQueryChunks = rateParser.splitInChunks();

        ExecutorService threadExecutor = Executors.newFixedThreadPool(5);

        List<Rate> rates = new ArrayList<>();

        rateQueryChunks.stream().forEach(rateQuery -> {
            threadExecutor.execute(() -> {
                yahooFinanaceManager.setExchangeQuery(rateQuery);
                final List<? extends ExchangeRate> providedRates = yahooFinanaceManager
                        .provideRate();

                providedRates.stream().map(o -> {
                    Map<String, String> collect = new HashMap<>().entrySet().stream().collect(Collectors.toMap(e -> o
                            .toCurrency(), e -> o.rate()));

                    Rate r = new Rate();
                    r.setKey(Rate.key(o.fromCurrency()));
                    r.setValue(collect);

                    return r;
                }).collect(Collectors.toList()).addAll(rates);
            });
        });

        threadExecutor.shutdown();
        threadExecutor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        rateRepo.save(rates);
    }
}
