package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.ingestion.rate.provider.*;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder.queryRatePattern;
import static org.mockito.Mockito.*;

/**
 * @author Georgi Lambov
 */
public class YahooExchangeServiceTest {

    private RateIngestion rateIngestion;
    @Mock
    private YahooFinanceManager yahooFinanaceManager;
    @Mock
    private YahooQueryRateBuilder queryRateBuilder;
    @Mock
    private RateRepository rateRepo;
    @Mock
    private YahooQueryRateParser rateParser;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);

        rateIngestion = new YahooExchangeService();

        ReflectionTestUtils.setField(rateIngestion, "yahooFinanaceManager",
                yahooFinanaceManager);

        ReflectionTestUtils.setField(rateIngestion, "queryRateBuilder",
                queryRateBuilder);
        ReflectionTestUtils.setField(rateIngestion, "rateRepo", rateRepo);
        ReflectionTestUtils.setField(rateIngestion, "rateParser", rateParser);

    }

    @Test
    public void provideRate() throws InterruptedException {

        when(rateParser.splitInChunks())
                .thenReturn(dataProviderForRateParser());

        doReturn(dataProviderForRates()).when(yahooFinanaceManager)
                .provideRate();

        rateIngestion.sync();

        verify(yahooFinanaceManager).provideRate();
        verify(queryRateBuilder).createQueryRate();
        verify(rateParser).splitInChunks();
    }

    private List<? extends ExchangeRate> dataProviderForRates() {

        YahooCSVBean usdGbp = new YahooCSVBean();
        usdGbp.setCurrency(String.format(
                YahooQueryRateBuilder.queryRatePattern, "USD", "GBP"));
        usdGbp.setRate("12.00");

        YahooCSVBean usdEur = new YahooCSVBean();
        usdEur.setCurrency(String.format(
                YahooQueryRateBuilder.queryRatePattern, "USD", "EUR"));
        usdEur.setRate("1.75");

        return Arrays.asList(usdGbp, usdEur);
    }

    private Set<String> dataProviderForRateParser() {
        String[] currencies = {"GBP", "EUR", "JPY"};

        String queryRate = Arrays.stream(currencies)
                .map(s -> String.format(queryRatePattern, "USD", s))
                .collect(Collectors.joining());

        Set<String> rates = new LinkedHashSet<>();
        rates.add(queryRate);

        return rates;
    }
}
