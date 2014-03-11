package org.apilytc.currency.persistence.domain;

import java.util.Map;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

// HashMap in redis
@RooJavaBean
@RooMongoEntity
@RooToString
public class Rate {

	private Map<String, String> value;

}
