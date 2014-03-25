package org.apilytic.currency.ingestion.rate.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
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
	public Set<Collection<String>> splitInChunks() {

		if (StringUtils.isBlank(queryRate)) {
			throw new IllegalArgumentException("queryRate can't be empty");
		}

		int entryLength = String.format(YahooQueryRateBuilder.queryRatePattern,
				"USD", "GBP").length();

		Set<Collection<String>> rateChunks = new HashSet<Collection<String>>();
		List<String> rates = new ArrayList<String>();

		for (int i = 0; i < queryRate.length(); i = i + entryLength) {
			rates.add(queryRate.substring(i, i + entryLength));

			if (rates.size() == (queryRate.length() / entryLength)) {
				rateChunks.clear();
				rateChunks.add(new ArrayList<String>(rates));
			}

			if (i > 0 && rates.size() > step) {
				// remove last element initial list to have proper chunk splits
				rates.remove(queryRate.substring(i, i + entryLength));
				List<String> section = new ArrayList<String>(rates);

				rates.clear();
				rateChunks.clear();

				rates.add(queryRate.substring(i, i + entryLength));

				rateChunks.add(section);
				rateChunks.add(new ArrayList<String>(rates));
			}
		}
		rates.clear();

		return rateChunks;
	}
}
