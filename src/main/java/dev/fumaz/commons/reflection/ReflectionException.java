package dev.fumaz.commons.reflection;

public class ReflectionException extends RuntimeException {

    /**
     * An exception that occurred whilst performing a reflection operation.
     *
     * @param message a short explanation of the exception being thrown
     */
    public ReflectionException(String message) {
        super(message);
    }

    /**
     * An exception that occurred whilst performing a reflection operation,
     * caused by another exception.
     *
     * @param message a short explanation of the exception being thrown
     * @param cause   the exception that caused this exception
     */
    public ReflectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
