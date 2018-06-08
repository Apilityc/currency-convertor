package org.apilytic.currency.persistence.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ISO_4217")
@XmlAccessorType(XmlAccessType.FIELD)
public class ISO4217 {

	@XmlElement(name = "HstrcCcyTbl")
	private CurrencyTable currencyTable;

	public CurrencyTable getCurrencyTable() {
		return currencyTable;
	}

}