package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

privileged aspect CurrencyExchange_Redis {

	declare @type: org.apilytic.currency.persistence.domain.CurrencyExchange :@RedisHash("currency_exchanges");

}
