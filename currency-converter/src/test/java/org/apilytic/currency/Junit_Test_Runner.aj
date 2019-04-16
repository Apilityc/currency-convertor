package org.apilytic.currency;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

public aspect Junit_Test_Runner {
	declare @type: !org.apilytic.currency..*IntegrationTest && org.apilytic.currency..*Test :@ExtendWith(MockitoExtension.class);
}