package org.apilytic.currency;

import static org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder.queryRatePattern;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.DataProvider;

/**
 * 
 * @author Georgi Lambov
 * 
 */
privileged aspect MarkIntegrationTests {

	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* : @RunWith(SpringJUnit4ClassRunner.class);
	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
	declare parents: org.apilytic.currency.ingestion.rate.*IntegrationTest* extends AbstractTestNGSpringContextTests;
	declare @type: org.apilytic.currency.ingestion.rate.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

	/**
	 * Provide query rate String (cross-reference) for Yahoo finance service.
	 */
	@DataProvider
	public Object[][] org.apilytic.currency.ingestion.rate.YahooExchangeServiceIntegrationTest.queryRateProvider() {
		String[] currencies = { "GBP", "EUR", "JPY" };

		String queryRate = "";
		for (String currency : currencies) {
			queryRate += String.format(queryRatePattern, "USD", currency);
		}

		return new Object[][] { { queryRate } };
	}

}
