package org.apilytic.currency.persistence.domain;

import org.springframework.data.redis.core.RedisHash;

privileged aspect Exchange_Redis {
	declare @type: org.apilytic.currency.persistence.domain.Exchange :@RedisHash("exchanges");
}
