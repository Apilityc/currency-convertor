package org.apilytc.currency.ingestion.rate.provider;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Georgi Lambov
 *
 */
privileged aspect RateProviderJunitAspect {

	declare @type: org.apilytc.currency.ingestion.rate.provider.*IntegrationTest* : @RunWith(SpringJUnit4ClassRunner.class);

	declare @type: org.apilytc.currency.ingestion.rate.provider.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

}
