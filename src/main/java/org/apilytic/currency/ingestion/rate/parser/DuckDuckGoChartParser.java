package org.apilytic.currency.ingestion.rate.parser;

import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("duckduckgo")
public class DuckDuckGoChartParser implements RateParser<DuckDuckGoChart> {

	@Override
	public String parse(DuckDuckGoChart chart) {
		return chart.getConversion().getConvertedAmount();
	}
}
