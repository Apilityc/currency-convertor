package org.apilytic.currency;

import io.reactivex.Flowable;
import org.apilytic.currency.ingestion.code.IsoCodesSyncher;
import org.apilytic.currency.ingestion.rate.RateSyncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

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

		String[] command = args[0].split(":");

		if (command[0].equals("ingestion")) {
			if (command[1].equals("currency")) {
				IsoCodesSyncher syncher = context.getBean(IsoCodesSyncher.class);
				syncher.sync();
			} else if (command[1].equals("exchange")) {
				RateSyncher syncher = context.getBean(RateSyncher.class);
				syncher.sync();
			}
		}

	}
}
