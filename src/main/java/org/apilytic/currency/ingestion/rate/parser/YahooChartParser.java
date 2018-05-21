package org.apilytic.currency.ingestion.rate.parser;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
@Primary
public class YahooChartParser implements RateParser<YahooChart> {

	public String parse(YahooChart chart) {
		AtomicReference<String> rate = new AtomicReference<>();

		chart.getResult().stream()
				.peek(s -> s.getIndicators().getAdjclose()
						.stream().peek(e -> e.getAdjclose().stream()
								.peek(rate::set)
								.findFirst())
						.findFirst())
				.findFirst();

		return rate.get();
	}
}
