package org.apilytc.currency.ingestion.vapor.model;

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
@XmlRootElement(name = "CcyNtry")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
public class CurrencyCode {

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
		CurrencyCode other = (CurrencyCode) obj;
		if (isoCode == null) {
			if (other.isoCode != null)
				return false;
		} else if (!isoCode.equals(other.isoCode))
			return false;
		return true;
	}

	@XmlElement(name = "Ccy")
	private String isoCode;

}
