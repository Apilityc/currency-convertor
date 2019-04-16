package org.apilytic.currency.ingestion.code;

import io.reactivex.Observable;
import org.apilytic.currency.persistence.domain.ISO4217;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class IsoCodesFetcher {

	@Autowired
	private RestTemplate restTemplate;

	@Value("${iso.codes.url}")
	private String isoCodesUrl;

	public ISO4217 fetch() {
		Optional.ofNullable(this.isoCodesUrl).orElseThrow(() -> new IllegalArgumentException("missing currency iso " +
				"codes uri"));

		ISO4217 iso = restTemplate.getForObject(this.isoCodesUrl, ISO4217.class);
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

	public void setIsoCodesUrl(String isoCodesUrl) {
		this.isoCodesUrl = isoCodesUrl;
	}
}
