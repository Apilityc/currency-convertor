package org.apilytic.currency;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Georgi Lambov
 * 
 */
privileged aspect MarkIntegrationOrUnitTestsForJunit {

	declare @type: org.apilytic.currency.csv.*IntegrationTest* : @RunWith(SpringJUnit4ClassRunner.class);
	declare @type: org.apilytic.currency.csv.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* : @RunWith(SpringJUnit4ClassRunner.class);
	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
	declare @type: org.apilytic.currency.ingestion.rate.*IntegrationTest* : @RunWith(SpringJUnit4ClassRunner.class);
	declare @type: org.apilytic.currency.ingestion.rate.*IntegrationTest* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

}
