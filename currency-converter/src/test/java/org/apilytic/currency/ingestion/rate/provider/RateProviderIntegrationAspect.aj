package org.apilytic.currency.ingestion.rate.provider;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;


/**
 * 
 * @author Georgi Lambov
 *
 */
privileged aspect RateProviderIntegrationAspect {

	declare parents: org.apilytic.currency.ingestion.rate.provider.*IntegrationTest* extends AbstractTestNGSpringContextTests;
	declare @type: org.apilytic.currency.ingestion.rate.provider.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

}
