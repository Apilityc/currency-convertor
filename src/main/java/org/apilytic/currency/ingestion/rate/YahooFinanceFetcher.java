package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.YahooChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Primary
class YahooFinanceFetcher implements RateFetch<YahooChart> {

	@Value("${yahoo.fetch.url}")
	private String url;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public YahooChart fetch(CurrencyPair pair) {

		url = url.replace("EUR", pair.from().toUpperCase())
				.replace("USD", pair.to().toUpperCase());

		YahooChart.Holder r = restTemplate.getForObject(url, YahooChart.Holder.class);

		return r.getChart();
	}
}
