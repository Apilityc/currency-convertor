package org.apilytc.currency.ingestion.vapor;

import java.util.HashSet;
import java.util.Set;

import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean;
import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean.CurrencyTable.CurrencyCode;
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

	public static final String ISO_CODES_URI = "http://www.currency-iso.org/dam/downloads/table_a1.xml";

	@Autowired
	private RestTemplate restTemplate;

	public ISO4217Bean fetch() {
		ISO4217Bean bean = restTemplate.getForObject(ISO_CODES_URI,
				ISO4217Bean.class);

		Set<CurrencyCode> cleanCodes = new HashSet<CurrencyCode>();
		for (CurrencyCode c : bean.getCurrencyTable().getCurrencyCodes()) {
			if (c.getIsoCode() == null || c.getIsoCode() != null
					&& c.getIsoCode().isEmpty()) {
				continue;
			}

			cleanCodes.add(c);
		}

		bean.getCurrencyTable().setCurrencyCodes(cleanCodes);

		cleanCodes = null;

		return bean;
	}

}
