package org.apilytic.currency.ingestion;

public interface IngestionSync {
	/**
	 * Syncs different ingestion types with underlying database.
	 */
	void sync();
}
