package org.apilytic.currency.persistence.domain;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.data.redis.core.RedisHash;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Currency_RedisTest {

	@Test
	public void valueRedisHashAnnotation() {
		Currency currency = new Currency();
		AspectJProxyFactory factory = new AspectJProxyFactory(currency);
		Currency_Redis aspect = new Currency_Redis();
		factory.addAspect(aspect);
		Object proxy = factory.getProxy();

		//TODO proper test for aspects to match @declare type annotation exactly
		assertEquals("currency", proxy.getClass().getAnnotation(RedisHash.class).value());
	}
}