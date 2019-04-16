package org.apilytic.currency.persistence.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "CcyTbl")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyTable {

	@XmlElement(name = "CcyNtry")
	private Set<CurrencyEntry> currencyEntries;

	public Set<CurrencyEntry> getCurrencyEntries() {
		return currencyEntries;
	}

	public void setCurrencyEntries(Set<CurrencyEntry> currencyEntries) {
		this.currencyEntries = currencyEntries;
	}
}