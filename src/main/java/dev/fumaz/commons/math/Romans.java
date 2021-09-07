package dev.fumaz.commons.math;

import java.util.TreeMap;

/**
 * @author Fumaz
 * @version 1.0
 * @see <a href="https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java/12968022">https://stackoverflow.com/questions/12967896/converting-integers-to-roman-numerals-java/12968022</a>
 * @since 1.0
 */
public final class Romans {

    private final static TreeMap<Long, String> map = new TreeMap<>();

    static {
        map.put(1000L, "M");
        map.put(900L, "CM");
        map.put(500L, "D");
        map.put(400L, "CD");
        map.put(100L, "C");
        map.put(90L, "XC");
        map.put(50L, "L");
        map.put(40L, "XL");
        map.put(10L, "X");
        map.put(9L, "IX");
        map.put(5L, "V");
        map.put(4L, "IV");
        map.put(1L, "I");
    }

    private Romans() {
    }

    /**
     * Converts an integer to its roman form.
     *
     * @param number the number
     * @return the roman string
     */
    public static String toRoman(long number) {
        long l = map.floorKey(number);

        if (number == l) {
            return map.get(number);
        }

        return map.get(l) + toRoman(number - l);
    }

}