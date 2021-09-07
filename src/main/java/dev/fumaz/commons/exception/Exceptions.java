package dev.fumaz.commons.exception;

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
