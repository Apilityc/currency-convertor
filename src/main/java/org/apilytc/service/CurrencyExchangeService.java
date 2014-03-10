/**
 * 
 */
package org.apilytc.service;

import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.apilytc.domain.CurrencyExchange;
import org.apilytc.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.JacksonJsonRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author Georgi Lambov
 * 
 */
@Service
@Primary
public class CurrencyExchangeService implements CurrencyExchangeRepository {

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	@Autowired
	private RedisTemplate<String, CurrencyExchange> template;

	@PostConstruct
	public void init() {
		template.setValueSerializer(new JacksonJsonRedisSerializer<CurrencyExchange>(
				CurrencyExchange.class));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public CurrencyExchange save(CurrencyExchange entity) {
		setOps.add(entity.getKey(), entity);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.CrudRepository#save(java.lang.Iterable
	 * )
	 */
	@Override
	public Iterable<CurrencyExchange> save(
			Iterable<? extends CurrencyExchange> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findOne(java.io.
	 * Serializable)
	 */
	@Override
	public CurrencyExchange findOne(String id) {
		Set<CurrencyExchange> members = setOps.members(id);
		return members.iterator().next();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#exists(java.io.
	 * Serializable)
	 */
	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Override
	public Iterable<CurrencyExchange> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#delete(java.io.
	 * Serializable)
	 */
	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.CrudRepository#delete(java.lang.Object
	 * )
	 */
	@Override
	public void delete(CurrencyExchange entity) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.data.repository.CrudRepository#delete(java.lang.Iterable
	 * )
	 */
	@Override
	public void delete(Iterable<? extends CurrencyExchange> entities) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

}
