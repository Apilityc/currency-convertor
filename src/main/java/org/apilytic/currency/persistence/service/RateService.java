package org.apilytic.currency.persistence.service;

import java.util.List;

import org.apilytic.currency.persistence.domain.Rate;
import org.apilytic.currency.persistence.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Primary
public class RateService implements RateRepository {

	@Autowired
	private RedisTemplate<String, String> template;

	@Override
	public Iterable<Rate> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<Rate> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rate save(Rate entity) {
		HashOperations<String, Object, Object> opsForHash = template
				.opsForHash();
		opsForHash.putAll(entity.getKey(), entity.getValue());

		return entity;
	}

	@Override
	public Iterable<Rate> save(Iterable<? extends Rate> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rate findOne(String id) {
		Rate e = new Rate();
		e.setKey(id);

		HashOperations<String, String, String> opsForHash = template
				.opsForHash();

		e.setValue(opsForHash.entries(id));
		return e;
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
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
	public void delete(Rate entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends Rate> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Rate> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
