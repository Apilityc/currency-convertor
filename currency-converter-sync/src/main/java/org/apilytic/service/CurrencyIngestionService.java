package org.apilytic.service;

import java.util.SortedSet;

import org.apilytic.model.TwitterMessage;

/**
 * Control flow of currency ingestion service updates.
 * 
 * @author Georgi Lambov
 * 
 */
public interface CurrencyIngestionService {

	void startCurrencyIngestion();

	void stopCurrencyIngestion();

	boolean isCurrencyIngestionRunning();

	SortedSet<TwitterMessage> getRateMessages();
}
