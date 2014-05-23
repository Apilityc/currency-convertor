package org.apilytic.currency.ingestion.rate.provider;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class YahooCSVBeanTest {

	private YahooCSVBean csvBean;

	@Before
	public void init() {
		csvBean = new YahooCSVBean();
	}

	@Test
	public void toCurrencyParsingWithCSVRow() {
		String csvRow = "SLLETB=X,0.0045,\"3/27/2014\",\"5:50am\"";
		csvBean.setCurrency(csvRow);
		assertEquals("SLL", csvBean.fromCurrency());
		assertEquals("ETB", csvBean.toCurrency());
	}

	@Test
	public void rateParsing() {
		csvBean.setRate("12.02");
		assertEquals("12.02", csvBean.rate());
	}
}
