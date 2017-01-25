package org.apilytic.currency.ingestion.vapor;

import org.apilytic.currency.ingestion.vapor.model.ISO4217Bean;
import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Sync ISO codes with database.
 *
 * @author Georgi Lambov
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

        Set<org.apilytic.currency.ingestion.vapor.model.CurrencyCode> currencyCodes = isoCode
                .getCurrencyTable().getCurrencyCodes();

        Set<CurrencyExchange> entities = currencyCodes.stream().map(o -> {
            CurrencyExchange entity = new CurrencyExchange();
            entity.setTitle(o.getIsoCode());
            return entity;
        }).collect(Collectors.toSet());

        currencyExchangeRepo.save(entities);
    }
}
