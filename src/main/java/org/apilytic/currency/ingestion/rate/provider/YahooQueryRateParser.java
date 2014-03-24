package org.apilytic.currency.ingestion.rate.provider;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * Parse in chunks Yahoo query rate.
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean
public class YahooQueryRateParser {

	public static final int DEFAULT_STEP = 10;

	private int step = DEFAULT_STEP;

	private String queryRate;

	/**
	 * Splits in query rate in chunks.
	 * 
	 * @return
	 */
	public Set<Set<String>> splitInChunks() {

		if (StringUtils.isBlank(queryRate)) {
			throw new IllegalArgumentException("queryRate can't be empty");
		}

		int entryLength = String.format(YahooQueryRateBuilder.queryRatePattern,
				"USD", "GBP").length();

		Set<Set<String>> rateChunks = new HashSet<Set<String>>();
		Set<String> rates = new HashSet<String>();
		for (int i = 0; i < queryRate.length(); i = i + entryLength) {
			rates.add(queryRate.substring(i, i + entryLength));

			// FIXME not working
			if (rates.size() == (queryRate.length() / entryLength)) {
				rateChunks.clear();
				rateChunks.add(new HashSet<String>(rates));
			}
			if (i > 0) {
				if (queryRate.length() % (i) < entryLength) {
					rateChunks.add(new HashSet<String>(rates));
				}
				if (rates.size() > step) {
					rates.clear();
					rates.add(queryRate.substring(i, i + entryLength));
					rateChunks.add(new HashSet<String>(rates));
				}
			}
			if (rates.size() > 1 && rates.size() < step) {
				rateChunks.clear();
				rateChunks.add(new HashSet<String>(rates));
			}
		}
		rates.clear();

		return rateChunks;
	}
}
