package org.apilytic.currency.ingestion.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

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
		DuckDuckGoChart chart = Mockito.mock(DuckDuckGoChart.class);
		DuckDuckGoChart.Conversion conversion = Mockito.mock(DuckDuckGoChart.Conversion.class);

		Mockito.when(pair.from()).thenReturn("usd");
		Mockito.when(pair.to()).thenReturn("eur");
		Mockito.when(restTemplate.exchange(URL, HttpMethod.GET, requestEntity, String.class)).thenReturn
				(responseEntity);
		Mockito.when(responseEntity.getBody()).thenReturn("ddg_spice_currency(response);");

		try {
			Mockito.when(mapper.readValue("response", DuckDuckGoChart.class)).thenReturn(chart);
		} catch (IOException e) {
			e.printStackTrace();
		}

		Mockito.when(chart.getConversion()).thenReturn(conversion);
		Mockito.when(conversion.getConvertedAmount()).thenReturn("1.1");

		DuckDuckGoChart fetch = fetcher.fetch(pair);

		assertNotNull(fetch);
		assertEquals("1.1", fetch.getConversion().getConvertedAmount());

		Mockito.verify(pair).from();
		Mockito.verify(pair).to();
		Mockito.verify(restTemplate).exchange(URL, HttpMethod.GET, requestEntity, String.class);
		Mockito.verify(responseEntity).getBody();
	}

	@Test
	public void fetchWrongResponse() throws IOException {
		Mockito.when(pair.from()).thenReturn("usd");
		Mockito.when(pair.to()).thenReturn("eur");
		Mockito.when(restTemplate.exchange(URL, HttpMethod.GET, requestEntity, String.class)).thenReturn
				(responseEntity);
		Mockito.when(responseEntity.getBody()).thenReturn("wrong;");

		Mockito.when(mapper.readValue("wrong;", DuckDuckGoChart.class)).thenThrow(new IOException());
		DuckDuckGoChart fetch = fetcher.fetch(pair);

		assertThrows(NullPointerException.class, () -> fetch.getConversion());

		Mockito.verify(restTemplate).exchange(URL, HttpMethod.GET, requestEntity, String.class);
		Mockito.verify(responseEntity).getBody();
		Mockito.verify(pair).from();
		Mockito.verify(pair).to();
	}
}