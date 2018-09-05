package org.apilytic.currency.persistence.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@XmlRootElement(name = "CcyNtry")
@XmlAccessorType(XmlAccessType.FIELD)
public class CurrencyEntry {

	@XmlElement(name = "Ccy")
	private String isoCode;

	@Override
	public int hashCode() {
		final int prime = 31;
		AtomicInteger result = new AtomicInteger(1);
		AtomicInteger code = new AtomicInteger(0);

		if (Optional.ofNullable(isoCode).isPresent()) {
			code.set(isoCode.hashCode());
		}

		result.set(prime * result.get() + code.get());

		return result.get();
	}

	public String getIsoCode() {
		return isoCode;
	}
}
