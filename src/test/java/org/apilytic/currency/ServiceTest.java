package org.apilytic.currency;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class ServiceTest {

	private Service service;

	@Before
	public void setUp() {
		service = new Service();
	}

	@Test
	public void outputString() {
		assertSame("a", service.output("a"));
	}

	@Test
	public void outputInt() {
		assertSame(1, service.output(1));
	}

}