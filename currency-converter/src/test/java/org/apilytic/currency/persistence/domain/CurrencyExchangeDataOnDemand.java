package org.apilytic.currency.persistence.domain;

import org.apache.commons.lang3.RandomStringUtils;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class CurrencyExchangeDataOnDemand {
	private List<CurrencyExchange> data;

	private Random rnd = new SecureRandom();

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

//	@Autowired
//	private RedisTemplate<String, CurrencyExchange> template;
//
//	@PostConstruct
//	public void wiring() {
//		template.setValueSerializer(new JacksonJsonRedisSerializer<CurrencyExchange>(
//				CurrencyExchange.class));
//	}

	public CurrencyExchange getNewTransientCurrencyExchange(int index) {
		CurrencyExchange obj = new CurrencyExchange();

		obj.setKey("currency:list");
//		obj.setIndex(String.valueOf(index));
		obj.setTitle(RandomStringUtils.randomAlphabetic(3));

		return obj;
	}

	public CurrencyExchange getRandomCurrencyExchange() {
		init();

		CurrencyExchange obj = data.get(rnd.nextInt(data.size()));
		return obj;
	}

	public void init() {
		int from = 0;
		int to = 10;

		data = new ArrayList<>();

		for (int i = from; i < to; i++) {

			if (currencyExchangeRepo.count() == 0) {
				continue;
			}

			CurrencyExchange next = currencyExchangeRepo.findAll().iterator().next();
			data.add(next);
		}

		if (!data.isEmpty()) {
			return;
		}

		for (int i = from; i < to; i++) {
			CurrencyExchange obj = getNewTransientCurrencyExchange(i);
			currencyExchangeRepo.save(obj);
			data.add(obj);
		}
	}

}
