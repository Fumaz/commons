package dev.fumaz.commons.math;

import com.google.common.collect.ImmutableList;
import dev.fumaz.commons.localization.Enums;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * All utilities that involve random number generation will be found here
 * Please note, most of the methods behave according to {@link ThreadLocalRandom}
 *
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Randoms {

    private Randoms() {
    }

    /**
     * Returns a pseudorandom {@code int} value between the specified
     * origin (inclusive) and the specified bound (exclusive).
     *
     * @param origin the least value returned
     * @param bound  the upper bound (exclusive)
     * @return a pseudorandom {@code int} value between the origin
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code origin} is greater than
     *                                  or equal to {@code bound}
     */
    public static int nextInt(int origin, int bound) {
        return getRandomInstance().nextInt(origin, bound);
    }

    /**
     * Returns a pseudorandom {@code int} value between zero (inclusive)
     * and the specified bound (exclusive).
     *
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return a pseudorandom {@code int} value between zero
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code bound} is not positive
     */
    public static int nextInt(int bound) {
        return getRandomInstance().nextInt(bound);
    }

    /**
     * Returns a pseudorandom {@code int} value.
     *
     * @return a pseudorandom {@code int} value
     */
    public static int nextInt() {
        return getRandomInstance().nextInt();
    }

    /**
     * Returns a pseudorandom {@code boolean} value.
     *
     * @return a pseudorandom {@code boolean} value
     */
    public static boolean nextBoolean() {
        return getRandomInstance().nextBoolean();
    }

    /**
     * Returns a pseudorandom {@code float} value between zero
     * (inclusive) and one (exclusive).
     *
     * @return a pseudorandom {@code float} value between zero
     * (inclusive) and one (exclusive)
     */
    public static float nextFloat() {
        return getRandomInstance().nextFloat();
    }

    /**
     * Returns a pseudorandom {@code long} value.
     *
     * @return a pseudorandom {@code long} value
     */
    public static long nextLong() {
        return getRandomInstance().nextLong();
    }

    /**
     * Returns a pseudorandom {@code long} value between zero (inclusive)
     * and the specified bound (exclusive).
     *
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return a pseudorandom {@code long} value between zero
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code bound} is not positive
     */
    public static long nextLong(long bound) {
        return getRandomInstance().nextLong(bound);
    }

    /**
     * Returns a pseudorandom {@code long} value between the specified
     * origin (inclusive) and the specified bound (exclusive).
     *
     * @param origin the least value returned
     * @param bound  the upper bound (exclusive)
     * @return a pseudorandom {@code long} value between the origin
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code origin} is greater than
     *                                  or equal to {@code bound}
     */
    public static long nextLong(long origin, long bound) {
        return getRandomInstance().nextLong(origin, bound);
    }

    /**
     * Returns a pseudorandom {@code double} value between zero
     * (inclusive) and one (exclusive).
     *
     * @return a pseudorandom {@code double} value between zero
     * (inclusive) and one (exclusive)
     */
    public static double nextDouble() {
        return getRandomInstance().nextDouble();
    }

    /**
     * Returns a pseudorandom {@code double} value between the specified
     * origin (inclusive) and bound (exclusive).
     *
     * @param origin the least value returned
     * @param bound  the upper bound (exclusive)
     * @return a pseudorandom {@code double} value between the origin
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code origin} is greater than
     *                                  or equal to {@code bound}
     */
    public static double nextDouble(double origin, double bound) {
        return getRandomInstance().nextDouble(origin, bound);
    }

    /**
     * Returns a pseudorandom {@code double} value between 0.0
     * (inclusive) and the specified bound (exclusive).
     *
     * @param bound the upper bound (exclusive).  Must be positive.
     * @return a pseudorandom {@code double} value between zero
     * (inclusive) and the bound (exclusive)
     * @throws IllegalArgumentException if {@code bound} is not positive
     */
    public static double nextDouble(double bound) {
        return getRandomInstance().nextDouble(bound);
    }

    /**
     * Returns a pseudorandom percentage between 0.0 and 100.0
     * and a certain amount of decimal precision
     *
     * @param decimals the amount of decimals
     * @return the percentage
     */
    public static double nextPercentage(int decimals) {
        double tenths = Math.pow(10, decimals);

        return nextInt((int) (100 * tenths) + 1) / tenths;
    }

    /**
     * Returns a stream producing the given {@code streamSize} number of
     * pseudorandom {@code int} values.
     *
     * @param streamSize the number of values to generate
     * @return a stream of pseudorandom {@code int} values
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero
     * @since 1.8
     */
    public static IntStream ints(long streamSize) {
        return getRandomInstance().ints(streamSize);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code int}
     * values.
     *
     * @return a stream of pseudorandom {@code int} values
     * @implNote This method is implemented to be equivalent to {@code
     * ints(Long.MAX_VALUE)}.
     * @since 1.8
     */
    public static IntStream ints() {
        return getRandomInstance().ints();
    }

    /**
     * Returns a stream producing the given {@code streamSize} number
     * of pseudorandom {@code int} values, each conforming to the given
     * origin (inclusive) and bound (exclusive).
     *
     * @param streamSize         the number of values to generate
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code int} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero, or {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @since 1.8
     */
    public static IntStream ints(long streamSize, int randomNumberOrigin,
                                 int randomNumberBound) {
        return getRandomInstance().ints(streamSize, randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code
     * int} values, each conforming to the given origin (inclusive) and bound
     * (exclusive).
     *
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code int} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @implNote This method is implemented to be equivalent to {@code
     * ints(Long.MAX_VALUE, randomNumberOrigin, randomNumberBound)}.
     * @since 1.8
     */
    public static IntStream ints(int randomNumberOrigin, int randomNumberBound) {
        return getRandomInstance().ints(randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns a stream producing the given {@code streamSize} number of
     * pseudorandom {@code long} values.
     *
     * @param streamSize the number of values to generate
     * @return a stream of pseudorandom {@code long} values
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero
     * @since 1.8
     */
    public static LongStream longs(long streamSize) {
        return getRandomInstance().longs(streamSize);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code long}
     * values.
     *
     * @return a stream of pseudorandom {@code long} values
     * @implNote This method is implemented to be equivalent to {@code
     * longs(Long.MAX_VALUE)}.
     * @since 1.8
     */
    public static LongStream longs() {
        return getRandomInstance().longs();
    }

    /**
     * Returns a stream producing the given {@code streamSize} number of
     * pseudorandom {@code long}, each conforming to the given origin
     * (inclusive) and bound (exclusive).
     *
     * @param streamSize         the number of values to generate
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code long} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero, or {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @since 1.8
     */
    public static LongStream longs(long streamSize, long randomNumberOrigin,
                                   long randomNumberBound) {
        return getRandomInstance().longs(streamSize, randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code
     * long} values, each conforming to the given origin (inclusive) and bound
     * (exclusive).
     *
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code long} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @implNote This method is implemented to be equivalent to {@code
     * longs(Long.MAX_VALUE, randomNumberOrigin, randomNumberBound)}.
     * @since 1.8
     */
    public static LongStream longs(long randomNumberOrigin, long randomNumberBound) {
        return getRandomInstance().longs(randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns a stream producing the given {@code streamSize} number of
     * pseudorandom {@code double} values, each between zero
     * (inclusive) and one (exclusive).
     *
     * @param streamSize the number of values to generate
     * @return a stream of {@code double} values
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero
     * @since 1.8
     */
    public static DoubleStream doubles(long streamSize) {
        return getRandomInstance().doubles(streamSize);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code
     * double} values, each between zero (inclusive) and one
     * (exclusive).
     *
     * @return a stream of pseudorandom {@code double} values
     * @implNote This method is implemented to be equivalent to {@code
     * doubles(Long.MAX_VALUE)}.
     * @since 1.8
     */
    public static DoubleStream doubles() {
        return getRandomInstance().doubles();
    }

    /**
     * Returns a stream producing the given {@code streamSize} number of
     * pseudorandom {@code double} values, each conforming to the given origin
     * (inclusive) and bound (exclusive).
     *
     * @param streamSize         the number of values to generate
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code double} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code streamSize} is
     *                                  less than zero
     * @throws IllegalArgumentException if {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @since 1.8
     */
    public static DoubleStream doubles(long streamSize, double randomNumberOrigin,
                                       double randomNumberBound) {
        return getRandomInstance().doubles(streamSize, randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns an effectively unlimited stream of pseudorandom {@code
     * double} values, each conforming to the given origin (inclusive) and bound
     * (exclusive).
     *
     * @param randomNumberOrigin the origin (inclusive) of each random value
     * @param randomNumberBound  the bound (exclusive) of each random value
     * @return a stream of pseudorandom {@code double} values,
     * each with the given origin (inclusive) and bound (exclusive)
     * @throws IllegalArgumentException if {@code randomNumberOrigin}
     *                                  is greater than or equal to {@code randomNumberBound}
     * @implNote This method is implemented to be equivalent to {@code
     * doubles(Long.MAX_VALUE, randomNumberOrigin, randomNumberBound)}.
     * @since 1.8
     */
    public static DoubleStream doubles(double randomNumberOrigin, double randomNumberBound) {
        return getRandomInstance().doubles(randomNumberOrigin, randomNumberBound);
    }

    /**
     * Returns a random element from a {@link List<T>}
     *
     * @param list the list
     * @param <T>  the type of the list
     * @return a random element, or null if the list is empty
     */
    public static <T> T choice(@NotNull List<T> list) {
        if (list.isEmpty()) {
            return null;
        }

        return list.get(nextInt(list.size()));
    }

    /**
     * Returns a random element from an {@link Iterable<T>}
     *
     * @param iterable the iterable
     * @param <T>      the type of the iterable
     * @return a random element, or null if the iterable has no elements
     */
    public static <T> T choice(@NotNull Iterable<T> iterable) {
        return choice(ImmutableList.copyOf(iterable));
    }

    /**
     * Returns a random element from an {@link Enum<T>}
     *
     * @param enumType the enum's class
     * @param <T>      the type of the enum
     * @return a random element, or null if the enum is empty
     */
    public static <T extends Enum<T>> T choice(@NotNull Class<T> enumType) {
        return choice(Enums.toList(enumType));
    }

    /**
     * Returns a random element from an array
     *
     * @param elements the elements
     * @param <T>      the type of the element
     * @return a random element, or null if there are no elements
     */
    @SafeVarargs
    public static <T> T choice(T... elements) {
        return choice(Arrays.asList(elements));
    }

    /**
     * Returns a certain amount of random elements from a {@link List}
     *
     * @param list   the list
     * @param amount the amount of elements
     * @param <T>    the type of the elements
     * @return a collection with the random elements
     */
    public static <T> Collection<T> choices(@NotNull List<T> list, int amount) {
        if (list.isEmpty()) {
            return Collections.emptyList();
        }

        return IntStream.range(0, amount)
                .mapToObj(i -> choice(list))
                .collect(Collectors.toList());
    }

    /**
     * Returns a certain amount of random elements from an {@link Iterable}
     *
     * @param iterable the iterable
     * @param amount   the amount of elements
     * @param <T>      the type of the elements
     * @return a collection with the random elements
     */
    public static <T> Collection<T> choices(@NotNull Iterable<T> iterable, int amount) {
        return choices(ImmutableList.copyOf(iterable), amount);
    }

    /**
     * Returns a certain amount of random elements from an {@link Enum<T>}
     *
     * @param enumType the enum's class
     * @param amount   the amount of elements
     * @param <T>      the type of the enum
     * @return a collection with the random elements
     */
    public static <T extends Enum<T>> Collection<T> choices(@NotNull Class<T> enumType, int amount) {
        return choices(Enums.toList(enumType), amount);
    }

    /**
     * Returns a certain amount of random elements from an array
     *
     * @param amount   the amount of elements
     * @param elements the elements
     * @param <T>      the type of the element
     * @return a collection with the random elements
     */
    @SafeVarargs
    public static <T> Collection<T> choices(int amount, T... elements) {
        return choices(Arrays.asList(elements), amount);
    }

    /**
     * Returns a certain amount of <b>unique</b> random elements from a {@link List}
     *
     * @param list   the list
     * @param amount the amount of elements
     * @param <T>    the type of the elements
     * @return a collection with the random <b>unique</b> elements
     */
    public static <T> Collection<T> sample(@NotNull List<T> list, int amount) {
        if (amount > list.size()) {
            throw new IllegalArgumentException("Sample amount cannot be higher than list size");
        }

        Set<T> samples = new HashSet<>();

        // This could be simplified to shuffling the list and returning a sublist?
        while (samples.size() < amount) {
            samples.add(choice(list));
        }

        return samples;
    }

    /**
     * Returns a certain amount of <b>unique</b> random elements from an {@link Iterable}
     *
     * @param iterable the iterable
     * @param amount   the amount of elements
     * @param <T>      the type of the elements
     * @return a collection with the random <b>unique</b> elements
     */
    public static <T> Collection<T> sample(@NotNull Iterable<T> iterable, int amount) {
        return sample(ImmutableList.copyOf(iterable), amount);
    }

    /**
     * Returns a certain amount of <b>unique</b> elements from an {@link Enum<T>}
     *
     * @param enumType the enum's class
     * @param amount   the amount of elements
     * @param <T>      the type of the enum
     * @return a collection with the random <b>unique</b> elements
     */
    public static <T extends Enum<T>> Collection<T> sample(@NotNull Class<T> enumType, int amount) {
        return sample(Enums.toList(enumType), amount);
    }

    /**
     * Returns a certain amount of <b>unique</b> elements from an array
     *
     * @param amount   the amount of elements
     * @param elements the elements
     * @param <T>      the type of the element
     * @return a collection with the random <b>unique</b> elements
     */
    @SafeVarargs
    public static <T> Collection<T> sample(int amount, T... elements) {
        return sample(Arrays.asList(elements), amount);
    }

    private static ThreadLocalRandom getRandomInstance() {
        return ThreadLocalRandom.current();
    }

}
