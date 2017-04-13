package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

/**
 * Created by g on 4/13/17.
 */
privileged aspect CurrencyExchange_Redis {

	declare @type: org.apilytic.currency.persistence.domain.CurrencyExchange :@RedisHash("currency_exchanges");

}
