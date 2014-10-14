package org.apilytic.currency.api.calc;

import java.util.Arrays;
import java.util.Locale;

import org.springframework.stereotype.Service;

/**
 * Created by g on 7/24/14.
 */
@Service
public class RateFormat {

	/**
	 * Cleanups provided currency for calculation.
	 *
	 * @param number
	 * @return
	 */
	public String cleanNumber(String number) {
		String charsAndWhiteSpaces = "[^\\d\\.,]|[\\s*-]";
		String commas = ",+?";

		if (number.matches(".*\\.\\d+$")) {
			return number.replaceAll(charsAndWhiteSpaces, "").replaceAll(
					commas, "");
		}

		return number.replaceAll(charsAndWhiteSpaces, "")
				.replaceAll(",(?!.*,)", ".").replaceAll(commas, "");
	}

	/**
	 * Verifies if passed language is valid and format currency according to it
	 * afterwards.
	 *
	 * @param locale
	 *            locale format in en_US - language and country
	 * @return
	 */
	public Locale verifyLocale(String locale) {

		if (locale == null) {
			return null;
		}
		
		String lang;
		String country;
		try {
			lang = locale.substring(0, 2);
			country = locale.substring(3, 5);
		} catch (StringIndexOutOfBoundsException e) {
			//TODO add logger
			return null;
		}

		if (Arrays.asList(Locale.getISOCountries()).contains(country) == false) {
			return null;
		}
		if (Arrays.asList(Locale.getISOLanguages()).contains(lang) == false) {
			return null;
		}

		return new Locale(lang, country);
	}
}