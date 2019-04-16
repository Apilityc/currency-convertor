package org.apilytic.currency;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public aspect Junit_Integration_Test_Runner {

	declare @type: org.apilytic.currency..*IntegrationTest :@ExtendWith(SpringExtension.class);
	declare @type: org.apilytic.currency..*IntegrationTest :@ContextConfiguration(locations =
			"classpath*:/META-INF/spring/applicationContext*.xml");

}
