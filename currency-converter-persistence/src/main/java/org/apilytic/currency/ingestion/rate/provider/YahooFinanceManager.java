package org.apilytic.currency.ingestion.rate.provider;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apilytic.currency.csv.CsvReaderAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.web.client.RestTemplate;

import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;

/**
 * Yahoo financial service provider.
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean(gettersByDefault = false)
public class YahooFinanceManager implements FinancialProvider {

	/**
	 * Query string in format ```&s=%s%s=X``` to send for which currencies we
	 * need exchange rates.
	 * 
	 * <ul>
	 * <li>&sUSDEUR=X - from USD to EUR</li>
	 * <li>&sUSDGBP=X - from USD to GBP</li>
	 * </ul>
	 */
	private String exchangeQuery;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CsvReaderAdapter csvStringReader;

	@Autowired
	private ColumnPositionMappingStrategy<YahooCSVBean> yahooCSVMappingStrategy;

	@Autowired
	private CsvToBean<YahooCSVBean> csvToBean;

	private static final String ENDPOINT = "http://download.finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1";

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apilytic.currency.ingestion.rate.provider.FinancialProvider#provideRate
	 * ()
	 */
	@Override
	public List<? extends ExchangeRate> provideRate() {
		if (StringUtils.isBlank(exchangeQuery)) {
			throw new IllegalArgumentException("exchangeQuery can't be empty");
		}

		String response = restTemplate.getForObject(ENDPOINT + exchangeQuery,
				String.class);

		csvStringReader.setStringReader(response);
		List<YahooCSVBean> yahooRates = csvToBean.parse(
				yahooCSVMappingStrategy,
				csvStringReader.createCsvReaderWithStringReader());

		return yahooRates;
	}
}
