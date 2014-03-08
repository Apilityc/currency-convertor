package org.apilytc.domain;


import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

aspect RateTimeTrackIntegrationTest_IntegrationTest {

	declare @type: RateTimeTrackIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: RateTimeTrackIntegrationTest: @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");

}
