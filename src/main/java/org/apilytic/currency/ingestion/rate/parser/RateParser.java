package org.apilytic.currency.ingestion.rate.parser;

public interface RateParser<T> {

	String parse(T e);
}
