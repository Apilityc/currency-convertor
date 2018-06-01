package org.apilytic.currency;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldTest {

	@InjectMocks
	private HelloWorld calc;

	@Mock
	private Service service;

	@Test
	public void plus() {
		when(service.output(1)).thenReturn(1);
		assertEquals(2, calc.sum(1, 1));
		verify(service, times(2)).output(1);
	}
}