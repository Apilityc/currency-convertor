package org.apilytic.currency.exchange;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertNull;

public class ExchangerTest {

	@Test
	public void exchange() {

		CurrencyPair pair = Mockito.mock(CurrencyPair.class);
		Exchanger exchanger = new Exchanger();
		assertNull(exchanger.exchange(pair));
	}
}