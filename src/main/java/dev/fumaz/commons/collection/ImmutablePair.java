package dev.fumaz.commons.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Represents a pair of <b>two</b> immutable elements.
 *
 * @param <T> the type of the first element
 * @param <Z> the type of the second element
 */
public class ImmutablePair<T, Z> {

    private final T first;
    private final Z second;

    public ImmutablePair(@Nullable T first, @Nullable Z second) {
        this.first = first;
        this.second = second;
    }

    @Nullable
    public T getFirst() {
        return first;
    }

    @Nullable
    public Z getSecond() {
        return second;
    }

    @NotNull
    public ImmutablePair<Z, T> flip() {
        return new ImmutablePair<>(second, first);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof ImmutablePair)) {
            return false;
        }

        ImmutablePair<?, ?> that = (ImmutablePair<?, ?>) other;
        return Objects.equals(first, that.first) && Objects.equals(second, that.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "ImmutablePair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
