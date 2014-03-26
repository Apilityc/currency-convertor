package org.apilytic.currency.ingestion.rate.provider;

import static org.apilytic.currency.ingestion.rate.provider.YahooQueryRateBuilder.queryRatePattern;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

		Set<String> expectedRates = parser.splitInChunks();
		expectedRates.contains(dataProvider.substring(0,
				YahooQueryRateParser.ENTRY_LENGTH * 3));

		assertEquals(1, expectedRates.size());
		String[] actualRates = expectedRates.toArray(new String[] { "0" });
		assertEquals(dataProvider, actualRates[0]);
	}

	@Test
	public void parseWithMoreThanDefaultStep() {
		String dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(2);
		parser.setQueryRate(dataProvider);

		Set<String> rates = parser.splitInChunks();

		assertEquals(2, rates.size());

		rates.contains(dataProvider.substring(0,
				YahooQueryRateParser.ENTRY_LENGTH * 2));
		rates.contains(dataProvider.substring(0, dataProvider.length()
				- YahooQueryRateParser.ENTRY_LENGTH));

		List<String> actualRates = new ArrayList<String>();
		Iterator<String> iterator = rates.iterator();
		while (iterator.hasNext()) {
			String el = iterator.next();
			actualRates.add(el);
		}

		String firstSection = String.format(queryRatePattern, "USD", "GBP");
		firstSection += String.format(queryRatePattern, "USD", "EUR");
		assertEquals(firstSection, actualRates.get(0));

		String secondSEction = String.format(queryRatePattern, "USD", "JPY");
		assertEquals(secondSEction, actualRates.get(1));
	}

	@Test
	public void parseWithEqualOfDefaultStep() {
		String dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(3);
		parser.setQueryRate(dataProvider);

		Set<String> expectedRates = parser.splitInChunks();

		assertEquals(1, expectedRates.size());
		String[] actualRates = expectedRates.toArray(new String[] { "0" });
		assertEquals(dataProvider, actualRates[0]);
	}

	private String dataProviderForQueryRateLessThanDefaultStep() {
		String[] currencies = { "GBP", "EUR", "JPY" };

		String queryRate = "";
		for (String currency : currencies) {
			queryRate += String.format(queryRatePattern, "USD", currency);
		}

		return queryRate;
	}

	private String dataProviderFor2CyclesAboveDefaultStep() {

		String[] currencies = { "GBP", "EUR", "JPY", "AUD", "CAD", "CHF" };

		String queryRate = "";
		for (String currency : currencies) {
			queryRate += String.format(queryRatePattern, "USD", currency);
		}

		return queryRate;

	}

}
