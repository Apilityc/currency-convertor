package org.apilytic.currency.persistence.domain;

import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
		Set h = new HashSet<>(Arrays.asList("usd", "gbp"));
		CurrencyExchange e = new CurrencyExchange();
		e.setTitles(h);

		repo.save(e);

		Set title = repo.findOne(e.getId()).getTitles();
		assertEquals(2, title.size());
	}

	@Test
	public void saveWithCustomId() {
		Set h = new HashSet<>(Arrays.asList("jpy", "eur"));

		CurrencyExchange e = new CurrencyExchange();
		e.setId("custom-id");
		e.setTitles(h);

		repo.save(e);

		Set title = repo.findOne("custom-id").getTitles();
		assertEquals(2, title.size());
	}

	@Test
	public void findAll() {
		Iterable<CurrencyExchange> all = repo.findAll();
		all.forEach(currency -> assertEquals(2, currency.getTitles().size()));
	}
}