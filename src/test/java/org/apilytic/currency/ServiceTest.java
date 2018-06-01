package org.apilytic.currency;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ServiceTest {

	private Service service;

	@BeforeEach
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