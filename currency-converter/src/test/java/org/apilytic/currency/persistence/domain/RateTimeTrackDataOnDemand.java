package org.apilytic.currency.persistence.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

@Component
public class RateTimeTrackDataOnDemand {

	private List<RateTimeTrack> data;

	private Random rnd = new SecureRandom();

	@Autowired
	private RedisTemplate<String, RateTimeTrack> template;

	public RateTimeTrack getNewTransientRateTimeTrack(int index) {
		RateTimeTrack obj = new RateTimeTrack();
		obj.setKey(RateTimeTrack.key());

		final Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		obj.setModifiedAt(calendar);

		return obj;
	}

	public RateTimeTrack getRandomRateTimeTrack() {
		init();
		RateTimeTrack obj = data.get(rnd.nextInt(data.size()));

		RateTimeTrack rateTimeTrack = new RateTimeTrack();
		rateTimeTrack.setKey(obj.getKey());

		final Calendar calendar = new GregorianCalendar();
		calendar.setTime(new Date());
		obj.setModifiedAt(calendar);

		ValueOperations<String, RateTimeTrack> opsForValue = template
				.opsForValue();
		opsForValue.set(obj.getKey(), rateTimeTrack);

		return rateTimeTrack;
	}

	public void init() {
		int from = 0;
		int to = 1;

		ValueOperations<String, RateTimeTrack> opsForValue = template
				.opsForValue();

		data = new ArrayList<RateTimeTrack>();

		// FIXME provide findAllImplementation
		for (int i = from; i < to; i++) {
			if (opsForValue.get(RateTimeTrack.key()) == null) {
				continue;
			}

			data.add(opsForValue.get(RateTimeTrack.key()));
		}

		if (!data.isEmpty()) {
			return;
		}

		for (int i = from; i < to; i++) {
			RateTimeTrack obj = getNewTransientRateTimeTrack(i);
			opsForValue.set(obj.getKey(), obj);
			data.add(obj);
		}
	}
}
