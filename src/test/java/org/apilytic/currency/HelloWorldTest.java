package org.apilytic.currency;

import org.apilytic.currency.exchange.Exchanger;
import org.apilytic.currency.ingestion.code.IsoCodesSyncher;
import org.apilytic.currency.ingestion.rate.RateSyncher;
import org.apilytic.currency.persistence.domain.CurrencyPair;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class HelloWorldTest {

	@InjectMocks
	private HelloWorld calc;

	@Mock
	private Service service;

	@Test
	public void plus() {
		when(service.output(1)).thenReturn(1);
		assertEquals(2, calc.sum(1, 1));
		verify(service, times(2)).output(1);
	}

	@Test
	public void executeIngestionIsoCodes() {
		ApplicationContext context = mock(ApplicationContext.class);
		IsoCodesSyncher isoCodeSyncher = mock(IsoCodesSyncher.class);

		when(context.getBean(IsoCodesSyncher.class)).thenReturn(isoCodeSyncher);

		calc.execute(new String[]{"ingestion:isocodes"}, context);

		verify(isoCodeSyncher).sync();
	}

	@Test
	public void executeIngestionExchange() {
		ApplicationContext context = mock(ApplicationContext.class);
		RateSyncher rateSyncher = mock(RateSyncher.class);

		when(context.getBean(RateSyncher.class)).thenReturn(rateSyncher);

		calc.execute(new String[]{"ingestion:exchange"}, context);

		verify(rateSyncher).sync();
	}

	@Test
	public void executeExchangeCurrency() {
		ApplicationContext context = mock(ApplicationContext.class);
		Exchanger exchanger = mock(Exchanger.class);
		CurrencyPair pair = mock(CurrencyPair.class);

		doReturn(pair).when(context).getBean(CurrencyPair.class);
		doReturn(exchanger).when(context).getBean(Exchanger.class);

		calc.execute(new String[]{"exchange:USDEUR"}, context);

//		verify(pair).setFrom("USD");
//		verify(pair).setTo("EUR");
		verify(exchanger).exchange(pair);
	}
}