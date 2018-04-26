package org.apilytic.currency.ingestion.rate;

import org.apilytic.currency.persistence.domain.YahooChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
class YahooFinanceFetcher implements RateFetch {

	private static final String URL = "https://query1.finance.yahoo" +
			".com/v8/finance/chart/EURUSD=X?region=US&lang=en-US&range=1d&includePrePost=false&interval=1d";

	@Autowired
	private RestTemplate restTemplate;

	public YahooChart fetch() {
		YahooChart.Holder r = restTemplate.getForObject(URL, YahooChart.Holder.class);
		return r.getChart();
	}
}
