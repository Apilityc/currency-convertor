package org.apilytc.currency.ingestion.vapor;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author Georgi Lambov
 *
 */
privileged aspect VaporJunitAspect {

	declare @type: org.apilytic.currency.ingestion.vapor.*Test* : @RunWith(SpringJUnit4ClassRunner.class);

	declare @type: org.apilytic.currency.ingestion.vapor.*Test* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
}
