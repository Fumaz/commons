package dev.fumaz.commons.collection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Represents a pair of <b>two</b> elements
 *
 * @param <T> the type of the first element
 * @param <Z> the type of the second element
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public class Pair<T, Z> {

    private T first;
    private Z second;

    public Pair(@Nullable T first, @Nullable Z second) {
        this.first = first;
        this.second = second;
    }

    @Nullable
    public T getFirst() {
        return first;
    }

    public void setFirst(@Nullable T first) {
        this.first = first;
    }

    @Nullable
    public Z getSecond() {
        return second;
    }

    public void setSecond(@Nullable Z second) {
        this.second = second;
    }

    @NotNull
    public Pair<Z, T> flip() {
        return new Pair<>(second, first);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof Pair)) {
            return false;
        }

        Pair<?, ?> pair = (Pair<?, ?>) other;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "Pair{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

}
