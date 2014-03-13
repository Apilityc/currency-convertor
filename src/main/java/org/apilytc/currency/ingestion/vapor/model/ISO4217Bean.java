package org.apilytc.currency.ingestion.vapor.model;

import java.util.List;

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
		private List<CurrencyCode> currencyCodes;

		public List<CurrencyCode> getCurrencyCodes() {
			return currencyCodes;
		}

		public void setCurrencyCodes(List<CurrencyCode> currencyCodes) {
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