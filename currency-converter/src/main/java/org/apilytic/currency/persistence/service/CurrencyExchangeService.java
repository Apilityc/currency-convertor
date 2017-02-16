/**
 *
 */
package org.apilytic.currency.persistence.service;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Set;

/**
 * @author Georgi Lambov
 */
@Service
@Primary
public class CurrencyExchangeService implements CurrencyExchangeRepository {

	@Resource(name = "redisTemplate")
	private SetOperations<String, CurrencyExchange> setOps;

	@Autowired
	private RedisTemplate<String, CurrencyExchange> template;

	@Override
	public <S extends CurrencyExchange> S save(S entity) {
		template.setValueSerializer(new Jackson2JsonRedisSerializer<CurrencyExchange>(CurrencyExchange.class));
		setOps.add(entity.getKey(), entity);
		return entity;
	}

	@Override
	public <S extends CurrencyExchange> Iterable<S> save(Iterable<S> entities) {
		CurrencyExchange[] values = new CurrencyExchange[]{};

		for (CurrencyExchange entity : entities) {
			// TODO create with annotation - postUpdate preUpdate kind of
			if (entity.getKey() == null) {
				// TODO: should we remove keystorage from DB.
				entity.setKey(CurrencyExchange.key());
			}

			CurrencyExchange[] updateValues = Arrays.copyOf(values,
					values.length + 1);
			updateValues[values.length] = entity;
			values = updateValues;

		}

		setOps.add(CurrencyExchange.key(), values);

		return (Iterable<S>) Arrays.asList(values);
	}

	@Override
	public CurrencyExchange findOne(String id) {
		Set<CurrencyExchange> members = setOps.members(id);
		return members.iterator().next();
	}

	@Override
	public boolean exists(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<CurrencyExchange> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<CurrencyExchange> findAll(Iterable<String> ids) {
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
	public void delete(CurrencyExchange entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Iterable<? extends CurrencyExchange> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * org.apilytic.currency.persistence.repository.CurrencyExchangeRepository
	 * #findAllCurencyExchanges()
	 */
	@Override
	public Set<CurrencyExchange> findAllCurencyExchanges() {
		return setOps.members(CurrencyExchange.key());
	}

}
