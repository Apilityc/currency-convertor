package org.apilytc.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.stereotype.Component;

@Component
public class CurrencyExchangeDataOnDemand {
	private List<CurrencyExchange> data;

	private Random rnd = new SecureRandom();

	@Autowired
	private RedisTemplate<String, CurrencyExchange> template;

	@PostConstruct
	public void wiring() {
		template.setValueSerializer(new JacksonJsonRedisSerializer<CurrencyExchange>(
				CurrencyExchange.class));
	}

	public CurrencyExchange getNewTransientCurrencyExchange(int index) {
		CurrencyExchange obj = new CurrencyExchange();
		obj.setKey(CurrencyExchange.key(String.valueOf(index)));
		obj.setTitle(RandomStringUtils.randomAlphabetic(3));

		return obj;
	}

	public CurrencyExchange getRandomRate() {
		init();

		CurrencyExchange obj = data.get(rnd.nextInt(data.size()));
		return obj;
	}

	public void init() {
		int from = 0;
		int to = 10;

		SetOperations<String, CurrencyExchange> opsForSet = template
				.opsForSet();

		data = new ArrayList<CurrencyExchange>();

		// FIXME provide findAllImplementation
		for (int i = from; i < to; i++) {

			if (opsForSet.members(String.valueOf(i)).size() == 0) {
				continue;
			}

			// FIXME work with multiple
			CurrencyExchange next = opsForSet.members(String.valueOf(i))
					.iterator().next();
			next.setKey(CurrencyExchange.key(String.valueOf(i)));
			data.add(next);
		}

		if (!data.isEmpty()) {
			return;
		}

		for (int i = from; i < to; i++) {
			CurrencyExchange obj = getNewTransientCurrencyExchange(i);
			opsForSet.add(CurrencyExchange.key(String.valueOf(i)), obj);
			data.add(obj);
		}
	}

}
