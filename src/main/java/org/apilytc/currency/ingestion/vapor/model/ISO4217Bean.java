package org.apilytc.currency.ingestion.vapor.model;

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
@XmlRootElement(name = "ISO_4217")
@XmlAccessorType(XmlAccessType.FIELD)
@RooJavaBean
public class ISO4217Bean {

	@XmlElement(name = "CcyTbl")
	private CurrencyTable currencyTable;

	/**
	 * 
	 * @author Georgi Lambov
	 * 
	 */
	@XmlRootElement(name = "CcyTbl")
	@XmlAccessorType(XmlAccessType.FIELD)
	// FIXME mapping of currency table to class
	public static class CurrencyTable {

		@XmlElement(name = "CcyNtry")
		private Set<CurrencyCode> currencyCodes;

		public Set<CurrencyCode> getCurrencyCodes() {
			return currencyCodes;
		}

		public void setCurrencyCodes(Set<CurrencyCode> currencyCodes) {
			this.currencyCodes = currencyCodes;
		}

		/**
		 * 
		 * @author Georgi Lambov
		 * 
		 */
		@XmlRootElement(name = "CcyNtry")
		@XmlAccessorType(XmlAccessType.FIELD)
		@RooJavaBean
		public static class CurrencyCode {

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result
						+ ((isoCode == null) ? 0 : isoCode.hashCode());
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

			public String getIsoCode() {
				return isoCode;
			}

			public void setIsoCode(String isoCode) {
				this.isoCode = isoCode;
			}

		}

	}
}