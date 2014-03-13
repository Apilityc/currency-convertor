package org.apilytc.currency.ingestion.vapor;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

privileged aspect VaporJunitAspect {

	declare @type: org.apilytc.currency.ingestion.vapor.*Test* : @RunWith(SpringJUnit4ClassRunner.class);

	declare @type: org.apilytc.currency.ingestion.vapor.*Test* : @ContextConfiguration(locations = "classpath*:/META-INF/spring/applicationContext*.xml");
}
