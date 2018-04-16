package org.apilytic.currency.persistence.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "CcyNtry")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyEntry {

	@XmlElement(name = "Ccy")
	private String isoCode;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((isoCode == null) ? 0 : isoCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrencyEntry other = (CurrencyEntry) obj;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		return true;
	}

}
