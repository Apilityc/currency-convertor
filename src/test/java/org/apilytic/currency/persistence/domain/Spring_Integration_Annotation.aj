package org.apilytic.currency.persistence.domain;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by g on 4/12/17.
 */
privileged aspect Spring_Integration_Annotation {

	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* :@RunWith(SpringRunner.class);
	declare @type: org.apilytic.currency.persistence.domain.*IntegrationTest* :@ContextConfiguration(locations =
			"classpath*:/META-INF/spring/applicationContext*.xml");

}
