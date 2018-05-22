package org.apilytic.currency.ingestion.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.when;

public class DuckDuckGoFetcherTest {

	@Mock
	private RestTemplate restTemplate;
	@Mock
	private ObjectMapper mapper;
	@Mock
	private HttpEntity requestEntity;
	@Mock
	private CurrencyPair pair;
	@Mock
	private ResponseEntity<String> responseEntity;

	private DuckDuckGoFetcher fetcher;

	private static final String URL = "https://duckduckgo/1/usd/eur";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);

		fetcher = new DuckDuckGoFetcher();

		ReflectionTestUtils.setField(fetcher, "mapper", mapper);
		ReflectionTestUtils.setField(fetcher, "requestEntity", requestEntity);
		ReflectionTestUtils.setField(fetcher, "restTemplate", restTemplate);
		ReflectionTestUtils.setField(fetcher, "url", URL);
	}

	@Test
	public void fetch() {
		when(pair.from()).thenReturn("usd");
		when(pair.to()).thenReturn("eur");
		when(restTemplate.exchange(URL, HttpMethod.GET, requestEntity, String.class)).thenReturn(responseEntity);
		when(responseEntity.getBody()).thenReturn("response");

		fetcher.fetch(pair);
	}
}