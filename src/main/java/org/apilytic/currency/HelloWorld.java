package org.apilytic.currency;

import io.reactivex.Flowable;
import org.apilytic.currency.exchange.Exchanger;
import org.apilytic.currency.ingestion.code.IsoCodesSyncher;
import org.apilytic.currency.ingestion.rate.RateSyncher;
import org.apilytic.currency.persistence.domain.CurrencyPair;
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

	public void execute(String[] args, ApplicationContext context) {
		String[] command = args[0].split(":");

		// format is command:option e.g. task:operation
		if (command[0].equals("ingestion")) {
			if (command[1].equals("isocodes")) {
				IsoCodesSyncher syncher = context.getBean(IsoCodesSyncher.class);
				syncher.sync();
			} else if (command[1].equals("exchange")) {
				RateSyncher syncher = context.getBean(RateSyncher.class);
				syncher.sync();
			}
		} else if (command[0].equals("exchange")) {
			CurrencyPair pair = context.getBean(CurrencyPair.class);

			pair.setFrom(command[1].substring(0, 3));
			pair.setTo(command[1].substring(3, 6));

			Exchanger exchanger = context.getBean(Exchanger.class);

			Flowable.just(exchanger.exchange(pair)).subscribe(System.out::println);
		}
	}

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext
				("classpath*:/META-INF/spring/applicationContext*.xml");
		Service s = context.getBean(Service.class);
		Flowable.just(s.output("Hello World!")).subscribe(System.out::println);

		HelloWorld w = new HelloWorld();
		w.execute(args, context);

	}
}
