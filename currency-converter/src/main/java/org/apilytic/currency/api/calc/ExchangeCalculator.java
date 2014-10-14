package org.apilytic.currency.api.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Locale;

import org.apilytic.currency.api.model.CurrencyRate;
import org.apilytic.currency.api.model.ExchangeCurrency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Calculate currency exchange rate for given currencies.
 * 
 * @author g
 *
 */
@Configurable
public class ExchangeCalculator {

	@Autowired
	private RateFormat rateFormat;
	
	/**
	 * Calculates exchange rates based on ratio and format output if its necessary.
	 * 
	 * 
	 * @return
	 */
	public ExchangeCurrency calc(CurrencyRate currencyRate, String rawRatio) {
		String amount = rateFormat.cleanNumber(currencyRate.getAmount());
        BigDecimal ratio = new BigDecimal(rawRatio);
        BigDecimal convertedAmount = ratio.multiply(new BigDecimal(amount));

        BigDecimal exchange = convertedAmount.setScale(2, RoundingMode.HALF_EVEN);
        ExchangeCurrency exchangeCurrency = new ExchangeCurrency();
        exchangeCurrency.setExchange(exchange.toString());

        //TODO refactor with aspectj
        Locale locale = rateFormat.verifyLocale(currencyRate.getLocale());
        if (locale != null) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
            exchangeCurrency.setExchange(currencyFormat.format(exchange));
        }
        
        return exchangeCurrency;
	}
	
}
