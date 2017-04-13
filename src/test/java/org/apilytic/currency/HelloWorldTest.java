package org.apilytic.currency;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by g on 4/3/17.
 */
public class HelloWorldTest {

	@InjectMocks
	private HelloWorld calc;

	@Mock
	private Service service;

	@Before
	public void setUp() {

		MockitoAnnotations.initMocks(this);
		ReflectionTestUtils.setField(calc, "service", service);
	}

	@Test
	public void plus() {
		when(service.output(1)).thenReturn(1);
		assertEquals(2, calc.sum(1, 1));
		verify(service, times(2)).output(1);
	}
}