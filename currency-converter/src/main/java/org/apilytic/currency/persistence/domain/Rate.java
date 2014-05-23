package org.apilytic.currency.persistence.domain;

import java.util.Map;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

// HashMap in redis
@RooJavaBean
@RooToString
public class Rate {

	private Map<String, String> value;

}
