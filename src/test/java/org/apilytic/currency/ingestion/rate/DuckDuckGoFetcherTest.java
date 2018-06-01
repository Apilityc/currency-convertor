package org.apilytic.currency.ingestion.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DuckDuckGoFetcherTest {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private ObjectMapper mapper;
	@Mock
	private HttpEntity requestEntity;
	@Mock
	private ResponseEntity<String> responseEntity;
	@Mock
	private CurrencyPair pair;

	@InjectMocks
	private DuckDuckGoFetcher fetcher = new DuckDuckGoFetcher();

	private static final String URL = "https://duckduckgo/1/usd/eur";

	@BeforeEach
	public void init() {
		ReflectionTestUtils.setField(fetcher, "url", URL);
	}

	@Test
	public void fetch() {
		DuckDuckGoChart chart = mock(DuckDuckGoChart.class);
		DuckDuckGoChart.Conversion conversion = mock(DuckDuckGoChart.Conversion.class);

		when(pair.from()).thenReturn("usd");
		when(pair.to()).thenReturn("eur");
		when(restTemplate.exchange(URL, HttpMethod.GET, requestEntity, String.class)).thenReturn(responseEntity);
		when(responseEntity.getBody()).thenReturn("ddg_spice_currency(response);");

		try {
			when(mapper.readValue("response", DuckDuckGoChart.class)).thenReturn(chart);
		} catch (IOException e) {
			e.printStackTrace();
		}

		when(chart.getConversion()).thenReturn(conversion);
		when(conversion.getConvertedAmount()).thenReturn("1.1");

		DuckDuckGoChart fetch = fetcher.fetch(pair);

		assertNotNull(fetch);
		assertEquals("1.1", fetch.getConversion().getConvertedAmount());

		verify(pair).from();
		verify(pair).to();
		verify(restTemplate).exchange(URL, HttpMethod.GET, requestEntity, String.class);
		verify(responseEntity).getBody();
	}
}