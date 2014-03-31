package org.apilytic.currency.persistence.service;

import javax.annotation.Resource;

import org.apilytic.currency.persistence.domain.RateTimeTrack;
import org.apilytic.currency.persistence.repository.RateTimeTrackRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RateTimeTrackService implements RateTimeTrackRepository {

	@Resource(name = "redisTemplate")
	ValueOperations<String, RateTimeTrack> opsForValue;

	@Override
	public <S extends RateTimeTrack> S save(S entity) {
		this.opsForValue.set(entity.getKey(), entity);
		return entity;
	}

	@Override
	public <S extends RateTimeTrack> Iterable<S> save(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public RateTimeTrack findOne(String id) {
		return this.opsForValue.get(id);
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
	public Iterable<RateTimeTrack> findAll(Iterable<String> ids) {
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
		this.opsForValue.getOperations().delete(id);

	}

	@Override
	public void delete(RateTimeTrack entity) {
		this.opsForValue.getOperations().delete(entity.getKey());
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
