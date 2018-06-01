package org.apilytic.currency.parser;

import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DuckDuckGoChartParserTest {

	@Test
	public void parse() {
		DuckDuckGoChart chart = mock(DuckDuckGoChart.class);
		DuckDuckGoChart.Conversion conversion = mock(DuckDuckGoChart.Conversion.class);

		when(chart.getConversion()).thenReturn(conversion);
		when(conversion.getConvertedAmount()).thenReturn("1.4");

		RateParser<DuckDuckGoChart> parser = new DuckDuckGoChartParser();

		String rate = parser.parse(chart);

		Assert.assertEquals("1.4", rate);

		verify(chart).getConversion();
		verify(conversion).getConvertedAmount();
	}
}