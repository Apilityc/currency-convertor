package org.apilytic.currency.persistence.domain;

import org.apilytic.currency.persistence.repository.CurrencyExchangeRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.test.RooIntegrationTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RooIntegrationTest(entity = CurrencyExchange.class, transactional = false)
public class CurrencyExchangeIntegrationTest {

	@Autowired
	private CurrencyExchangeRepository currencyExchangeRepo;

	@Autowired
	private CurrencyExchangeDataOnDemand dod;

//	@Test
//	public void save() {
//		assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to initialize correctly",
//				dod.getRandomCurrencyExchange());
//
//		CurrencyExchange obj = dod
//				.getNewTransientCurrencyExchange(Integer.MAX_VALUE);
//		Assert.assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
//				obj);
//
//		CurrencyExchange actual = currencyExchangeRepo.save(obj);
//
//		assertEquals(obj.getKey(), actual.getKey());
//		assertEquals(obj.getTitle(), actual.getTitle());
//	}
//
//	@Test
//	public void saveWithArray() {
//		assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to initialize correctly",
//				dod.getRandomCurrencyExchange());
//
//		CurrencyExchange obj1 = dod.getNewTransientCurrencyExchange(11);
//		Assert.assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
//				obj1);
//
//		CurrencyExchange obj2 = dod.getNewTransientCurrencyExchange(12);
//		Assert.assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to provide a new transient entity",
//				obj2);
//
//		List<CurrencyExchange> asList = Arrays.asList(obj1, obj2);
//		Iterable<CurrencyExchange> actual = currencyExchangeRepo.save(asList);
//
//		int i = 0;
//		for (CurrencyExchange entity : actual) {
//			assertEquals(asList.get(i).getKey(), entity.getKey());
//			assertEquals(asList.get(i).getTitle(), entity.getTitle());
//			++i;
//		}
//	}
//
//	@Test
//	public void findOne() {
//		CurrencyExchange obj = dod.getRandomCurrencyExchange();
//		assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to initialize correctly",
//				obj);
//		String key = obj.getKey();
//		assertNotNull(
//				"Data on demand for 'CurrencyExchange failed to provide an identifier",
//				key);
//		obj = currencyExchangeRepo.findOne(key);
//		assertNotNull(
//				"Find method for 'CurrencyExchange illegally returned null for id '"
//						+ key + "'", obj);
//		assertEquals(
//				"Find method for 'CurrencyExchange returned the incorrect identifier",
//				key, obj.getKey());
//	}
//
	@Test
	public void repositorySave() {
		CurrencyExchange e = new CurrencyExchange();
		e.setTitle("repo title");
		e.setKey("repo_key");

		currencyExchangeRepo.save(e);

		assertEquals("repo title", currencyExchangeRepo.findOne("repo_key").getTitle());
	}
}
