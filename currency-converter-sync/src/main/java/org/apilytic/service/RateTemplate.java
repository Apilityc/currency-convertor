package org.apilytic.service;

import java.util.Date;

import org.apilytic.model.TwitterMessage;

public class RateTemplate {
	public TwitterMessage retrieveRates() {
		TwitterMessage t = new TwitterMessage();
		t.setCreatedAt(new Date());
		t.setFromUser("my user");
		t.setId(Long.MIN_VALUE);
		t.setProfileImageUrl("profile user");
		t.setText("this is my test");

		return t;
	}
}
