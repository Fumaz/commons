package dev.fumaz.commons.exception;

/**
 * A {@link java.util.function.Supplier} that can throw an {@link Exception}
 */
@FunctionalInterface
public interface ExceptionSupplier<T> {

    T get() throws Exception;

}
