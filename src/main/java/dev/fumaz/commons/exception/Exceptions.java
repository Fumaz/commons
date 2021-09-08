package dev.fumaz.commons.exception;

import java.util.Optional;

/**
 * A utility to deal with exceptions
 *
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Exceptions {

    private Exceptions() {
    }

    /**
     * Wraps a supplier so that it optionally returns a value instead of throwing an {@link Exception}
     *
     * @param supplier the supplier
     * @param <T>      the type of the supplied object
     * @return the optional result
     */
    public static <T> Optional<T> optional(ExceptionSupplier<T> supplier) {
        try {
            return Optional.of(supplier.get());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    /**
     * Wraps a supplier so that it returns {@code null} instead of throwing an {@link Exception}
     *
     * @param supplier the supplier
     * @param <T>      the type of the supplied object
     * @return the object or null if an exception was thrown
     */
    public static <T> T orNull(ExceptionSupplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Wraps a runnable so that it throws {@link RuntimeException} instead of {@link Exception}
     *
     * @param runnable the runnable
     */
    public static void wrap(ExceptionRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
    }

    /**
     * Wraps a runnable so that it doesn't throw any {@link Exception} and just ignores them
     *
     * @param runnable the runnable
     */
    public static void wrapIgnoring(ExceptionRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception ignored) {
        }
    }

    /**
     * Wraps a runnable so that it always logs exceptions without throwing them
     *
     * @param runnable the runnable
     */
    public static void wrapLogging(ExceptionRunnable runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Throws the exception, <b>always</b> as a {@link RuntimeException}
     *
     * @param throwable the throwable
     */
    public static RuntimeException propagate(Throwable throwable) {
        throw Exceptions.wrapThrowable(throwable);
    }

    /**
     * Wraps any {@link Throwable} to a {@link RuntimeException}
     *
     * @param throwable the {@link Throwable}
     * @return the {@link RuntimeException}
     */
    public static RuntimeException wrapThrowable(Throwable throwable) {
        if (throwable instanceof RuntimeException) {
            return (RuntimeException) throwable;
        }

        return new RuntimeException(throwable);
    }

}
