package org.apilytic.currency.ingestion.vapor.model;

import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.roo.addon.javabean.RooJavaBean;

/**
 * 
 * @author Georgi Lambov
 * 
 */
@XmlRootElement(name = "CcyTbl")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
// FIXME exclude this class within mapping - with xpath or oder way.
public class CurrencyTable {

	@XmlElement(name = "CcyNtry")
	private Set<CurrencyCode> currencyCodes;

}