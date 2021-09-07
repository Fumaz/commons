package dev.fumaz.commons.exception;

/**
 * A {@link Runnable} that can throw an {@link Exception}
 */
@FunctionalInterface
public interface ExceptionRunnable {

    void run() throws Exception;

}
