package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

privileged aspect Currency_Redis {

	declare @type: org.apilytic.currency.persistence.domain.Currency :@RedisHash("currency_exchanges");

}
