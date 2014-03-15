package org.apilytic.currency.persistence.domain;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


public privileged aspect RateIntegrationTest_IntegrationTest {

	declare @type: RateIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: RateIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
	
}
