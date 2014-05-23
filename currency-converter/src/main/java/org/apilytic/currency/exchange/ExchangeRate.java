package org.apilytic.currency.exchange;

import java.util.Map;
import java.util.Set;

import org.apilytic.currency.persistence.domain.CurrencyExchange;
import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@RooJavaBean
public class ExchangeRate {

	private Map<String, Set<CurrencyExchange>> rates;
}
