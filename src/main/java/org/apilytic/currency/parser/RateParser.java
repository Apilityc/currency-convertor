package org.apilytic.currency.parser;

public interface RateParser<T> {

	String parse(T chart);
}
