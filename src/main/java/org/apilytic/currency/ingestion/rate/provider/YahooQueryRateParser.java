package org.apilytic.currency.ingestion.rate.provider;

import java.util.LinkedHashSet;
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
	public static final int ENTRY_LENGTH = String.format(
			YahooQueryRateBuilder.queryRatePattern, "USD", "GBP").length();

	private int step = DEFAULT_STEP;

	private String queryRate;

	/**
	 * Splits in query rate in chunks.
	 * 
	 * @return
	 */
	public Set<String> splitInChunks() {

		if (StringUtils.isBlank(queryRate)) {
			throw new IllegalArgumentException("queryRate can't be empty");
		}

		Set<String> rateChunks = new LinkedHashSet<String>();
		String rates = "";

		// TODO check what will be results if logic is inverted - remove put all
		// elements to list at start
		for (int i = 0; i < queryRate.length(); i = i + ENTRY_LENGTH) {
			rates += queryRate.substring(i, i + ENTRY_LENGTH);

			// at bring of section - save to chunks
			if ((rates.length() / ENTRY_LENGTH) == (queryRate.length() / ENTRY_LENGTH)) {
				rateChunks.add(rates);
			}

			if (rateChunks.size() > 0 && (rates.length() / ENTRY_LENGTH) > step) {
				rateChunks.clear();
				String section = rates.substring(0, step * ENTRY_LENGTH);
				rateChunks.add(section);

				for (int j = 0; j < queryRate.length(); j = j + ENTRY_LENGTH) {

					if (j % step != 0) {
						continue;
					}

					int endPosition = j + j;
					if (j + j > queryRate.length()) {
						endPosition = queryRate.length();
					}

					if (endPosition > 0) {
						rateChunks.add(queryRate.substring(j, endPosition));
					}
				}

			}
		}
		rates = "";

		return rateChunks;
	}
}
