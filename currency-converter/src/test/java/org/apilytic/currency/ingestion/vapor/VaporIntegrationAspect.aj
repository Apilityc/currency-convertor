package org.apilytic.currency.ingestion.vapor;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * 
 * @author Georgi Lambov
 * 
 */
privileged aspect VaporIntegrationAspect {

	declare parents: org.apilytic.currency.ingestion.vapor.*Test*  extends AbstractTestNGSpringContextTests;
	declare @type: org.apilytic.currency.ingestion.vapor.*Test* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

	@Autowired
	RedisTemplate<String, CurrencyExchange> ISOCodesFromISOdotComSyncerIntegrationTest.template;

}
