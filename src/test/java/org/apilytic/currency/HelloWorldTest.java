package org.apilytic.currency;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by g on 4/3/17.
 */
public class HelloWorldTest {

	private HelloWorld calc;

	@Before
	public void setUp() {
		calc = new HelloWorld();
	}

	@Test
	public void plus() {
		assertEquals(2, calc.sum(1, 1));
	}
}