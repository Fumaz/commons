package dev.fumaz.commons.collection;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * A holder for a variable that doesn't get
 * computed until it's fetched
 *
 * @param <T> the type of the value
 */
public abstract class LazyValue<T> {

    private T value;

    public static <T> LazyValue<T> of(Supplier<T> supplier) {
        return new SuppliedLazyValue<>(supplier);
    }

    public static <T> LazyValue<T> of(T value) {
        return new SuppliedLazyValue<>(() -> value);
    }

    protected abstract T compute();

    public T get() {
        if (value == null) {
            value = compute();
        }

        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof LazyValue)) {
            return false;
        }

        LazyValue<?> lazyValue = (LazyValue<?>) other;
        return Objects.equals(value, lazyValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "LazyValue{" +
                "value=" + get() +
                '}';
    }

    private static class SuppliedLazyValue<T> extends LazyValue<T> {
        private final Supplier<T> supplier;

        public SuppliedLazyValue(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        @Override
        protected T compute() {
            return supplier.get();
        }
    }

}
