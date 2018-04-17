package org.apilytic.currency.ingestion.code;

import org.apilytic.currency.persistence.domain.CurrencyEntry;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Set;
import java.util.stream.Collectors;

public class IsoCodesFetcher {

	@Autowired
	private RestTemplate restTemplate;

	private String isoCodesUri;

	public ISO4217 fetch() {
		if (isoCodesUri == null) {
			throw new IllegalArgumentException("missing currency iso codes uri");
		}

		ISO4217 iso = restTemplate.getForObject(isoCodesUri, ISO4217.class);

		Set<CurrencyEntry> cleanCodes = iso.getCurrencyTable().getCurrencyEntries()
				.stream()
				.filter(c -> StringUtils.hasText(c.getIsoCode()))
				.collect(Collectors.toSet());

		iso.getCurrencyTable().setCurrencyEntries(cleanCodes);

		return iso;
	}

	public void setIsoCodesUri(String isoCodesUri) {
		this.isoCodesUri = isoCodesUri;
	}
}
