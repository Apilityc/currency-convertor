package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class YahooFinanceFetcher implements RateFetch {

	@Value("${yahoo.fetch.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	public YahooChart fetch(String currencyPair) {
		YahooChart.Holder r = restTemplate.getForObject(
				url.replace("EURUSD", currencyPair),
				YahooChart.Holder.class);

		return r.getChart();
	}
}
