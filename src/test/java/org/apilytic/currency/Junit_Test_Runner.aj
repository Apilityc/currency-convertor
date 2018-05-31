package org.apilytic.currency;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

public aspect Junit_Test_Runner {
	declare @type: org.apilytic.currency..*Test :@RunWith(MockitoJUnitRunner.class);
}