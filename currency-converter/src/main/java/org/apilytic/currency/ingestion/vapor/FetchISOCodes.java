package org.apilytic.currency.ingestion.vapor;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apilytic.currency.ingestion.vapor.model.CurrencyCode;
import org.apilytic.currency.ingestion.vapor.model.ISO4217Bean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Create web request and retrieve list with codes.
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class FetchISOCodes {

	//FIXME move to properties file
	public static final String ISO_CODES_URI = "https://www.currency-iso.org/dam/downloads/lists/list_one.xml";

	@Autowired
	private RestTemplate restTemplate;

	public ISO4217Bean fetch() {
		ISO4217Bean bean = restTemplate.getForObject(ISO_CODES_URI,
				ISO4217Bean.class);

		Set<CurrencyCode> cleanCodes = new HashSet<CurrencyCode>();
		for (CurrencyCode c : bean.getCurrencyTable().getCurrencyCodes()) {

			if (StringUtils.isBlank(c.getIsoCode())) {
				continue;
			}

			cleanCodes.add(c);
		}

		bean.getCurrencyTable().setCurrencyCodes(cleanCodes);

		cleanCodes = null;

		return bean;
	}

}
