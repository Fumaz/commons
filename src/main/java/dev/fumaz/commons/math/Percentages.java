package dev.fumaz.commons.math;

import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Percentages {

    private Percentages() {
    }

    public static boolean isHigherThan(double chance) {
        return nextPercentage(chance) > chance;
    }

    public static boolean isLowerThan(double chance) {
        return nextPercentage(chance) < chance;
    }

    public static boolean isAtLeast(double chance) {
        return nextPercentage(chance) >= chance;
    }

    public static boolean isAtMost(double chance) {
        return nextPercentage(chance) <= chance;
    }

    public static boolean isExactly(double chance) {
        return nextPercentage(chance) == chance;
    }

    @Nullable
    public static <T> T random(Map<T, Double> map) {
        double percentage = nextPercentage(3);
        double total = 0.0D;

        for (T t : map.keySet()) {
            double chance = map.get(t);
            total += chance;

            if (total < percentage) {
                continue;
            }

            return t;
        }

        return null;
    }

    private static double nextPercentage(double base) {
        return Randoms.nextPercentage(Decimals.getDecimalPlaces(base));
    }

}
