package org.apilytic.currency;

import io.reactivex.Flowable;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by g on 4/2/17.
 */
public class HelloWorld {

	public int sum(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext
				("classpath*:/META-INF/spring/applicationContext*.xml");
		Service s = context.getBean(Service.class);
		Flowable.just(s.output("Hello World!")).subscribe(System.out::println);
	}
}
