package dev.fumaz.commons.interfaces;

@FunctionalInterface
public interface ConsumerSupplier<T, Z> {

    Z get(T value);

}
