package org.apilytic.currency.persistence.domain;

import java.util.Calendar;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.tostring.RooToString;

// value in Redis JSON
@RooJavaBean
@RooToString
public class RateTimeTrack {

	private Calendar modifiedAt;
}