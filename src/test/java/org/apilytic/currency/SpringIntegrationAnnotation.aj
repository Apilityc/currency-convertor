package org.apilytic.currency;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by g on 4/12/17.
 */
privileged aspect SpringIntegrationAnnotation {
	declare @type: org.apilytic.currency.*IntegrationTest* :@RunWith(SpringRunner.class);
	declare @type: org.apilytic.currency.*IntegrationTest* :@ContextConfiguration(locations =
			"classpath*:/META-INF/spring/applicationContext*.xml");
}
