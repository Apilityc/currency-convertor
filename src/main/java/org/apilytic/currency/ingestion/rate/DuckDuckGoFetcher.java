package org.apilytic.currency.ingestion.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apilytic.currency.persistence.domain.DuckDuckGoChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service
public class DuckDuckGoFetcher {

	@Value("${duckduckgo.fetch.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	public DuckDuckGoChart fetch(String currencyPair) {

		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.add("User-Agent", "curl");
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		ResponseEntity<String> r = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);

		String jsonString = r.getBody()
				.replace("ddg_spice_currency(", "")
				.replace(");", "");

		ObjectMapper mapper = new ObjectMapper();
		DuckDuckGoChart chart = null;

		try {
			chart = mapper.readValue(jsonString, DuckDuckGoChart.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return chart;
	}
}
