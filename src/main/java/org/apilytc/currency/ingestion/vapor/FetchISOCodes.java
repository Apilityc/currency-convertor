package org.apilytc.currency.ingestion.vapor;

import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean;
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
		return restTemplate.getForObject(ISO_CODES_URI, ISO4217Bean.class);
	}

}
