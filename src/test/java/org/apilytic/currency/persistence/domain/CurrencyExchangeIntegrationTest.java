package org.apilytic.currency.persistence.domain;

import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.junit.Assert.assertEquals;

/**
 * Created by g on 4/13/17.
 */
@Rollback
public class CurrencyExchangeIntegrationTest {

	@Autowired
	private CurrencyExchangeRepository repo;

	@Test
	public void save() {
		CurrencyExchange e = new CurrencyExchange();
		e.setTitle("repo title");

		repo.save(e);

		assertEquals("repo title", repo.findOne(e.getId()).getTitle());
	}
}