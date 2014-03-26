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

		// TODO simplify
		for (int i = 0; i < queryRate.length(); i = i + ENTRY_LENGTH) {
			rates += queryRate.substring(i, i + ENTRY_LENGTH);

			// at bring of section - save to chunks
			if ((rates.length() / ENTRY_LENGTH) == (queryRate.length() / ENTRY_LENGTH)) {
				rateChunks.add(rates);
			}

			// remove last element from the previous section and put it in new
			// section
			if (i > 0 && (rates.length() / ENTRY_LENGTH) > step) {

				String section = rates.substring(0, rates.length()
						- ENTRY_LENGTH);

				rates = (queryRate.substring(i, i + ENTRY_LENGTH));
				rateChunks.clear();
				rateChunks.add(section);
				rateChunks.add(rates);

			}
		}
		rates = "";

		return rateChunks;
	}
}
