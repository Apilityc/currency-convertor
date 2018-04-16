package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.ISO4217;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

public class IsoCodesFetcher {

	@Autowired
	private RestTemplate restTemplate;

	private String isoCodesUri;

	public ISO4217 fetch() {
		if (isoCodesUri == null) {
			throw new IllegalArgumentException("missing currency iso codes uri");
		}

		ISO4217 iso = restTemplate.getForObject(isoCodesUri, ISO4217.class);

//		Set<CurrencyEntry> cleanCodes = iso.getCurrencyTable().getCurrencyCodes()
//				.stream()
//				.filter(c -> !StringUtils.isBlank(c.getIsoCode()))
//				.collect(Collectors.toSet());
//		iso.getCurrencyTable().setCurrencyCodes(cleanCodes);
//
		return iso;
	}

	public void setIsoCodesUri(String isoCodesUri) {
		this.isoCodesUri = isoCodesUri;
	}
}
