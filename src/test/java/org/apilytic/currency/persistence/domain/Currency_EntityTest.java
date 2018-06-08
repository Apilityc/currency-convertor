package org.apilytic.currency.persistence.domain;

import org.junit.jupiter.api.Test;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Currency_EntRityTest {

	@Test
	public void testId() {
		Currency currency = new Currency();
		AspectJProxyFactory factory = new AspectJProxyFactory(currency);
		Currency_Entity aspect = new Currency_Entity();

		factory.addAspect(aspect);
		Currency proxy = factory.getProxy();

		proxy.setId("id");
		assertEquals("id", proxy.getId());
	}
}