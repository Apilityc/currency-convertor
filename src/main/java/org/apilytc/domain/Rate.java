package org.apilytc.domain;

import java.util.Map;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.layers.repository.mongo.RooMongoEntity;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooMongoEntity
@RooToString
public class Rate {

	private Map<String, String> value;

}
