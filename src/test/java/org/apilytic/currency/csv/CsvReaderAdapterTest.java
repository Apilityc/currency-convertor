package org.apilytic.currency.csv;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

import au.com.bytecode.opencsv.CSVReader;

/**
 * 
 * @author Georgi Lambov
 * 
 */
public class CsvReaderAdapterTest {

	private CsvReaderAdapter adapter;

	@Before
	public void init() {
		adapter = new CsvReaderAdapter();
	}

	@Test
	public void testCreateCsvReaderWithStringReaderWithExistingString() {
		adapter.setStringReader("csv content");
		CSVReader csvReader = adapter.createCsvReaderWithStringReader();
		assertNotNull(csvReader);
	}

	@Test(expected = NullPointerException.class)
	public void testCreateCsvReaderWithStringReaderWithMissingString() {
		CSVReader csvReader = adapter.createCsvReaderWithStringReader();
		assertNotNull(csvReader);
	}

}
