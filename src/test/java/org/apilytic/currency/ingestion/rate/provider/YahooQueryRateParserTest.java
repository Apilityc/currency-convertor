package org.apilytic.currency.ingestion.rate.provider;

import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooQueryRateParserTest {

	private YahooQueryRateParser parser;

	@Before
	public void init() {
		parser = new YahooQueryRateParser();
	}

	@Test
	public void parseWithLessThanDefaultStep() {

		String dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setQueryRate(dataProvider);

		Set<Collection<String>> expectedRates = parser.splitInChunks();

		System.out.println(expectedRates);
		assertEquals(1, expectedRates.size());
	}

	@Test
	public void parseWithMoreThanDefaultStep() {
		String dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(2);
		parser.setQueryRate(dataProvider);

		Set<Collection<String>> expectedRates = parser.splitInChunks();

		System.out.println(expectedRates);
		assertEquals(2, expectedRates.size());
	}

	@Test
	public void parseWithEqualOfDefaultStep() {
		String dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(3);
		parser.setQueryRate(dataProvider);

		Set<Collection<String>> expectedRates = parser.splitInChunks();

		System.out.println(expectedRates);
		assertEquals(1, expectedRates.size());
	}

	private String dataProviderForQueryRateLessThanDefaultStep() {
		String[] currencies = { "GBP", "EUR", "JPY" };

		String queryRate = "";
		for (String currency : currencies) {
			queryRate += String.format(YahooQueryRateBuilder.queryRatePattern,
					"USD", currency);
		}

		return queryRate;
	}

}
