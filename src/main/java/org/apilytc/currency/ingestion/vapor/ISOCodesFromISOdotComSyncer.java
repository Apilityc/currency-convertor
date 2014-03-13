package org.apilytc.currency.ingestion.vapor;

import java.util.ArrayList;
import java.util.List;

import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean;
import org.apilytc.currency.ingestion.vapor.model.ISO4217Bean.CurrencyTable.CurrencyCode;
import org.apilytc.currency.persistence.domain.CurrencyExchange;
import org.apilytc.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sync iso codes with database.
 * 
 * @author Georgi Lambov
 * 
 */
@Service
public class ISOCodesFromISOdotComSyncer implements VaporIngestion {

	@Autowired
	private FetchISOCodes fetchISOCodes;

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apilytc.currency.ingestion.vapor.VaporIngestion#sync()
	 */
	@Override
	public void sync() {
		ISO4217Bean isoCode = fetchISOCodes.fetch();

		List<CurrencyCode> currencyCodes = isoCode.getCurrencyTable()
				.getCurrencyCodes();

		// FIXME why 181 records are only saved instead all 211
		List<CurrencyExchange> entities = new ArrayList<CurrencyExchange>();
		for (CurrencyCode currencyCode : currencyCodes) {
			CurrencyExchange entity = new CurrencyExchange();
			entity.setTitle(currencyCode.getIsoCode());
			entities.add(entity);
		}

		currencyExchangeRepo.save(entities);
	}
}
