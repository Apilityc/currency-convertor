package org.apilytc.service;

import org.apilytc.domain.RateTimeTrack;
import org.apilytc.repository.RateTimeTrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RateTimeTrackService implements RateTimeTrackRepository {

	@Autowired
	private RedisTemplate<String, RateTimeTrack> template;

	@Override
	public RateTimeTrack save(RateTimeTrack entity) {
		ValueOperations<String, RateTimeTrack> opsForValue = template
				.opsForValue();

		opsForValue.set(entity.getKey(), entity);
		return entity;
	}

	@Override
	public Iterable<RateTimeTrack> save(
			Iterable<? extends RateTimeTrack> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RateTimeTrack findOne(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<RateTimeTrack> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(RateTimeTrack entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends RateTimeTrack> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
