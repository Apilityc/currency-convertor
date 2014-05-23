package org.apilytic.currency.csv;

import java.io.StringReader;

import org.springframework.roo.addon.javabean.RooJavaBean;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Wrapper to be injected in as service.
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean(gettersByDefault = false)
public class CsvReaderAdapter {

	private String stringReader;

	/**
	 * Creates CSVReader from StringReader.
	 * 
	 * @return
	 */
	public CSVReader createCsvReaderWithStringReader() {
		return new CSVReader(new StringReader(stringReader));
	}
}
