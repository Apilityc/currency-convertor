package org.apilytic.currency;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

/**
 * Created by g on 4/3/17.
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations =
		"classpath*:/META-INF/spring/applicationContext*.xml")
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