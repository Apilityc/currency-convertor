package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.ISO4217;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import static org.mockito.Mockito.verify;

public class IsoCodesFetcherTest {

	@Mock
	private RestTemplate restTemplate;
	private IsoCodesFetcher fetcher;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		fetcher = new IsoCodesFetcher();
		ReflectionTestUtils.setField(fetcher, "restTemplate", restTemplate);
	}

	@Test(expected = IllegalArgumentException.class)
	public void missingUri() {
		fetcher.fetch();
	}

	@Test
	public void fetchCodes() {
		fetcher.setIsoCodesUri("https://uri");
		fetcher.fetch();

		verify(restTemplate).getForObject("https://uri", ISO4217.class);
	}
}