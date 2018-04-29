package org.apilytic.currency.ingestion.rate.parser;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class YahooChartParser {

	String parse(YahooChart chart) {
		AtomicReference<String> rate = new AtomicReference<>();

		chart.getResult().stream()
				.peek(s -> s.getIndicators().getAdjclose()
						.stream().peek(e -> e.getAdjclose().stream()
								.peek(rate::set)
								.findFirst())
						.findFirst())
				.findFirst();

//		chart.getResult().stream().peek(s -> rate.set("1")).findFirst();

//		chart.getResult().stream()
//				.findFirst().get().getIndicators().getAdjclose()
//				.stream().findFirst().get()
//				.getAdjclose().stream()
//				.peek(rate::set)
//				.findFirst();

		return rate.get();
	}
}
