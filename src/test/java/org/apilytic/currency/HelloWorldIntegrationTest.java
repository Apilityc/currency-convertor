package org.apilytic.currency;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

/**
 * Created by g on 4/13/17.
 */
public class HelloWorldIntegrationTest {

	@Autowired
	private HelloWorld world;

	@Test
	public void calc() {
		assertEquals(2, world.sum(1, 1));
	}
}
