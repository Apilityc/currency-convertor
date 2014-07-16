package org.apilytic.currency.api.model;

import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * Resulting model from exchange query request.
 * Created by g on 7/5/14.
 */
@RooJavaBean
public class CurrencyRate {

    //TODO Possible switch between CurrencyuRate and ExchangeRate names and implementations.
    /**
     * Resulting query from exchange.
     */
    private String exchange;

}
