package org.apilytic.currency.persistence.domain;

import com.fasterxml.jackson.annotation.JsonRootName;

import java.util.List;

@JsonRootName("adjclose")
public class YahooAdjClose {

	private List adjclose;

	public List getAdjclose() {
		return adjclose;
	}
}
