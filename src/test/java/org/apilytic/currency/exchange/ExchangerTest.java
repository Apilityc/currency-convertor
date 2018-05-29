package org.apilytic.currency.exchange;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangerTest {

	@Test
	public void exchange() {
		Exchanger exchanger = new Exchanger();

		ExchangeRepository exchangeRepository = mock(ExchangeRepository.class);
		Exchange exchange = mock(Exchange.class);
		CurrencyPair pair = mock(CurrencyPair.class);

		ReflectionTestUtils.setField(exchanger, "exchangeRepository", exchangeRepository);

		when(pair.to()).thenReturn("EUR");
		when(pair.from()).thenReturn("USD");
		when(exchangeRepository.findOne("USD")).thenReturn(exchange);

		Map rates = new HashMap() {{
			put("USD", "1.9");
			put("EUR", "1.8");
		}};

		when(exchange.getRates()).thenReturn(rates);

		System.out.println(exchanger.exchange(pair));
		assertEquals("1.8", exchanger.exchange(pair));
	}
}