package org.apilytic.currency.exchange;

import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.apilytic.currency.persistence.domain.Exchange;
import org.apilytic.currency.persistence.repository.ExchangeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ExchangerTest {

	@InjectMocks
	private Exchanger exchanger;
	@Mock
	private ExchangeRepository exchangeRepository;

	@Test
	public void exchange() {
		Exchange exchange = mock(Exchange.class);
		CurrencyPair pair = mock(CurrencyPair.class);

		when(pair.to()).thenReturn("EUR");
		when(pair.from()).thenReturn("USD");
		when(exchangeRepository.findOne("USD")).thenReturn(exchange);

		Map rates = new HashMap() {{
			put("USD", "1.9");
			put("EUR", "1.8");
		}};

		when(exchange.getRates()).thenReturn(rates);

		assertEquals("1.8", exchanger.exchange(pair));
	}
}