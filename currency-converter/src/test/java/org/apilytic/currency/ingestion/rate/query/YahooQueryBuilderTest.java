package org.apilytic.currency.ingestion.rate.query;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class YahooQueryBuilderTest {

    private YahooQueryBuilder queryBuilder;

    @BeforeClass
    public void init() {
        queryBuilder = new YahooQueryBuilder();
    }

    @Test
    public void createQueryRate() {
        assertEquals(queryBuilder.createQueryRate("USD", "EUR"), "&s=USDEUR=X");
    }

}