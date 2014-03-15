package org.apilytic.currency.ingestion.rate.provider;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.web.client.RestTemplate;

/**
 * Yahoo financial service provider.
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean(gettersByDefault = false)
public class YahooFinance implements FinancialProvider {

	/**
	 * Query string in format ```&s=%s%s=X``` to send for which currencies we
	 * need exchange rates.
	 * 
	 * <ul>
	 * <li>&sUSDEUR=X - EUR to GBP</li>
	 * <li>&sUSDGBP=X - USD to EUR</li>
	 * </ul>
	 */
	private String exchangeQuery;

	@Autowired
	private RestTemplate restTemplate;

	private static final String ENDPOINT = "http://download.finance.yahoo.com/d/quotes.csv?e=.csv&f=sl1d1t1";

	@Override
	public String provideRate() {
		if (StringUtils.isBlank(exchangeQuery)) {
			throw new IllegalArgumentException("exchangeQuery can't be empty");
		}

		String response = restTemplate.getForObject(ENDPOINT + exchangeQuery,
				String.class);
		return response;
	}

}
