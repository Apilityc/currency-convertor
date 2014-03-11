package org.apilytc.currency.persistence.domain;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

privileged aspect CurrencyExchangeIntegrationTest_IntegrationTest {

	declare @type: CurrencyExchangeIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);

	declare @type: CurrencyExchangeIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

}
