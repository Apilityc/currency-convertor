package org.apilytic.currency.parser;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.apilytic.currency.persistence.domain.YahooChart.Result;
import org.apilytic.currency.persistence.domain.YahooChart.Result.Indicators;
import org.apilytic.currency.persistence.domain.YahooChart.Result.Indicators.AdjClose;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class YahooChartParserTest {

	@Test
	public void parse() {
		YahooChart yahooChart = mock(YahooChart.class);
		Result result = mock(Result.class);
		Indicators indicators = mock(Indicators.class);
		AdjClose adjClose = mock(AdjClose.class);

		when(yahooChart.getResult()).thenReturn(Arrays.asList(result));
		when(result.getIndicators()).thenReturn(indicators);
		when(indicators.getAdjclose()).thenReturn(Arrays.asList(adjClose));
		when(adjClose.getAdjclose()).thenReturn(Arrays.asList("1"));

		RateParser parser = new YahooChartParser();

		parser.parse(yahooChart);

		verify(yahooChart).getResult();
	}

}