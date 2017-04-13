package org.apilytic.currency;

import io.reactivex.Flowable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by g on 4/2/17.
 */
@Component
public class HelloWorld {

	@Autowired
	private Service service;

	public int sum(int a, int b) {
		return service.output(a) + service.output(b);
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext
				("classpath*:/META-INF/spring/applicationContext*.xml");
		Service s = context.getBean(Service.class);
		Flowable.just(s.output("Hello World!")).subscribe(System.out::println);
	}
}
