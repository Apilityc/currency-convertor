package org.apilytic.currency.ingestion.vapor.model;

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
@XmlRootElement(name = "ISO_4217")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
public class ISO4217Bean {

	@XmlElement(name = "CcyTbl")
	private CurrencyTable currencyTable;

}