package org.apilytic.currency.api;

import org.springframework.stereotype.Service;

/**
 * Created by g on 7/24/14.
 */
@Service
public class ConvertRateFormat {

    /**
     * Cleanups provided currency for calculation.
     *
     * @param number
     * @return
     */
    public String cleanNumber(String number) {
        return number.replaceAll("[^\\d\\.,]|[\\s*-]", "").replaceAll(",(?!.*,)", ".").replaceAll(",+?", "");
    }
}