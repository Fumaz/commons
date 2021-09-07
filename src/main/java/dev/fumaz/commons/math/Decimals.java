package dev.fumaz.commons.math;

import java.math.BigDecimal;

/**
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Decimals {

    private Decimals() {
    }

    /**
     * Returns the amount of decimals in a double
     *
     * @param number the decimal number
     * @return the amount of decimals
     */
    public static int getDecimalPlaces(double number) {
        if (number % 1 == 0) {
            return 0; // The number is an integer
        }

        return BigDecimal.valueOf(number).scale();
    }

}
