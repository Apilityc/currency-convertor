package org.apilytic.currency.ingestion.code;

import io.reactivex.Observable;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Set;

public class IsoCodesFetcher {

	@Autowired
	private RestTemplate restTemplate;

	private String isoCodesUri;

	public ISO4217 fetch() {
		if (isoCodesUri == null) {
			throw new IllegalArgumentException("missing currency iso codes uri");
		}

		ISO4217 iso = restTemplate.getForObject(isoCodesUri, ISO4217.class);
		Set cleanCodes = new HashSet();

		/* streams
		cleanCodes = iso.getCurrencyTable().getCurrencyEntries()
				.stream()
				.filter(c -> StringUtils.hasText(c.getIsoCode()))
				.collect(Collectors.toSet());
		*/

		/* print codes
		Observable.fromIterable(iso.getCurrencyTable().getCurrencyEntries())
				.filter(c -> StringUtils.hasText(c.getIsoCode()))
				.flatMap(c -> Observable.just(c.getIsoCode()))
				.subscribe(System.out::println);
		*/


		Observable.fromIterable(iso.getCurrencyTable().getCurrencyEntries())
				.filter(c -> StringUtils.hasText(c.getIsoCode()))
				.subscribe(c -> cleanCodes.add(c));

		iso.getCurrencyTable().setCurrencyEntries(cleanCodes);

		return iso;
	}

	@Required
	public void setIsoCodesUri(String isoCodesUri) {
		this.isoCodesUri = isoCodesUri;
	}
}
