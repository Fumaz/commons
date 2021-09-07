package dev.fumaz.commons.math;

public final class Maths {

    private Maths() {
    }

    public static Integer parseIntOrNull(String string) {
        return Maths.parseIntOrDefault(string, null);
    }

    public static Long parseLongOrNull(String string) {
        return Maths.parseLongOrDefault(string, null);
    }

    public static Integer parseIntOrDefault(String string, Integer defaultValue) {
        try {
            return Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Long parseLongOrDefault(String string, Long defaultValue) {
        try {
            return Long.parseLong(string);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
