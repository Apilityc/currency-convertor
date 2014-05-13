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

		StringBuilder dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setQueryRate(dataProvider);

		Set<String> expectedRates = parser.splitInChunks();
		expectedRates.contains(dataProvider.substring(0,
				YahooQueryRateParser.ENTRY_LENGTH * 3));

		assertEquals(1, expectedRates.size());
		String[] actualRates = expectedRates.toArray(new String[] { "0" });
		assertEquals(dataProvider.toString(), actualRates[0]);
	}

	@Test
	public void parseWithMoreThanSetStepAndNotEquallyDevitedToStep() {
		StringBuilder dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(3);
		parser.setQueryRate(dataProvider);

		Set<String> rates = parser.splitInChunks();
		System.out.println(rates);
		assertEquals(3, rates.size());

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
		firstSection += String.format(queryRatePattern, "CHF", "GBP");
		firstSection += String.format(queryRatePattern, "CAD", "GBP");
		assertEquals(firstSection, actualRates.get(0));

		String secondSection = String.format(queryRatePattern, "USD", "EUR");
		secondSection += String.format(queryRatePattern, "CHF", "EUR");
		secondSection += String.format(queryRatePattern, "CAD", "EUR");
		assertEquals(secondSection, actualRates.get(1));
	}

	@Test
	public void parseWithEqualOfDefaultStep() {
		StringBuilder dataProvider = dataProviderForQueryRateLessThanDefaultStep();
		parser.setStep(3);
		parser.setQueryRate(dataProvider);

		Set<String> actualRates = parser.splitInChunks();
		assertEquals(3, actualRates.size());
		String[] rates = actualRates.toArray(new String[] { "0" });
		assertEquals(dataProvider.toString(), rates[0] + rates[1] + rates[2]);
	}

	@Test
	public void parseWithOneCyclesAboveDefaultStep() {
		StringBuilder dataProvider = dataProviderFor2CyclesAboveDefaultStep();
		parser.setStep(3);
		parser.setQueryRate(dataProvider);

		Set<String> rates = parser.splitInChunks();
		assertEquals(2, rates.size());

		List<String> actualRates = new ArrayList<String>();
		Iterator<String> iterator = rates.iterator();
		while (iterator.hasNext()) {
			String el = iterator.next();
			actualRates.add(el);
		}

		String firstSection = String.format(queryRatePattern, "USD", "GBP");
		firstSection += String.format(queryRatePattern, "USD", "EUR");
		firstSection += String.format(queryRatePattern, "USD", "JPY");
		assertEquals(firstSection, actualRates.get(0).toString());

		String secondSection = String.format(queryRatePattern, "USD", "AUD");
		secondSection += String.format(queryRatePattern, "USD", "CAD");
		secondSection += String.format(queryRatePattern, "USD", "CHF");
		assertEquals(secondSection, actualRates.get(1));
	}

	private StringBuilder dataProviderForQueryRateLessThanDefaultStep() {
		String[] currencies = { "GBP", "EUR", "JPY" };

		StringBuilder queryRate = new StringBuilder();
		for (String currency : currencies) {
			queryRate.append(String.format(queryRatePattern, "USD", currency));
			queryRate.append(String.format(queryRatePattern, "CHF", currency));
			queryRate.append(String.format(queryRatePattern, "CAD", currency));
			// queryRate.append(String.format(queryRatePattern, "MXN",
			// currency));
		}

		return queryRate;
	}

	private StringBuilder dataProviderFor2CyclesAboveDefaultStep() {

		String[] currencies = { "GBP", "EUR", "JPY", "AUD", "CAD", "CHF" };

		StringBuilder queryRate = new StringBuilder();
		for (String currency : currencies) {
			queryRate.append(String.format(queryRatePattern, "USD", currency));
		}

		return queryRate;

	}

}
