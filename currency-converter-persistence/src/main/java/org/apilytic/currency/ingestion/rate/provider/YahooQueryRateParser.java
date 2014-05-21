package org.apilytic.currency.ingestion.rate.provider;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.stereotype.Service;

/**
 * Parse in chunks Yahoo query rate.
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean
@Service
public class YahooQueryRateParser {

	public static final int DEFAULT_STEP = 200;
	public static final int ENTRY_LENGTH = String.format(
			YahooQueryRateBuilder.queryRatePattern, "USD", "GBP").length();

	private int step = DEFAULT_STEP;

	private StringBuilder queryRate;

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
		//TODO String buffer
		StringBuilder rates = new StringBuilder();
		
		// used in NEXT CHUNK string last position of each chunk
		int lastChunkPosition = 0;
		for (int i = 0; i < queryRate.length(); i = i + ENTRY_LENGTH) {

			// FIRST CHUNK
			if (i == 0) {
				
				//TODO Use one for statement.
				for (int stepIteration = 0; stepIteration < step; stepIteration++) {
					//assign only first chunk with size of the ``step``
					int endPos = (stepIteration * ENTRY_LENGTH) + ENTRY_LENGTH;
					int startPos = stepIteration * ENTRY_LENGTH;

					if (endPos > queryRate.length()) {
						break;
					}
					rates.append(queryRate.substring(startPos, endPos));
				}
				rateChunks.add(rates.toString());
			}

			// NEXT CHUNK
			if (i > step * ENTRY_LENGTH) {
				// clear rates query
				rates = new StringBuilder();
				for (int stepIteration = 0; stepIteration < step; stepIteration++) {
					// stepSection valid value executed once.
					int stepSection = i + (ENTRY_LENGTH * stepIteration);
					// first iteration after initial load doesn't have chunk
					// last position
					if (lastChunkPosition > 0) {
						stepSection = lastChunkPosition
								+ (ENTRY_LENGTH * (stepIteration + 1));
					}

					int startPos = stepSection - ENTRY_LENGTH;
					int endPos = stepSection;

					if (endPos > queryRate.length()) {
						break;
					}

					rates.append(queryRate.substring(startPos, endPos));

					// retrieve last position of the read string
					if (stepIteration + 1 == step) {
						lastChunkPosition = endPos;
					}
				}

				if (rates.length() == 0) {
					break;
				}

				rateChunks.add(rates.toString());
			}
		}
		rates = new StringBuilder();;

		return rateChunks;
	}
}
