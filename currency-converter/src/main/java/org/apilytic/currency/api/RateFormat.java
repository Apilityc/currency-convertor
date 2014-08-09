package org.apilytic.currency.api;

import org.springframework.stereotype.Service;

import java.util.Locale;

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
            return number.replaceAll(charsAndWhiteSpaces, "").replaceAll(commas, "");
        }

        return number.replaceAll(charsAndWhiteSpaces, "").replaceAll(",(?!.*,)", ".").replaceAll(commas, "");
    }

    /**
     * Verifies if passed language is valid and format currency according to it afterwards.
     *
     * @return
     */
    public Locale verifyLocale(String language) {

        for (String locale : Locale.getISOLanguages()) {
            if (language.equals(locale)) {
                return new Locale(language);
            }
        }
        return null;
    }
}