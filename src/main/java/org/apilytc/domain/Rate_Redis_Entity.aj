package org.apilytc.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Persistent;

public privileged aspect Rate_Redis_Entity {

	public static String Rate.KEY = "rate:%s";

	public static String Rate.key(String key) {
		return String.format(Rate.KEY, key);
	}

	private String Rate.key;

	public String Rate.getKey() {
		return this.key;
	}

	public void Rate.setKey(String key) {
		this.key = key;

	}
}