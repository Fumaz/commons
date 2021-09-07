package dev.fumaz.commons.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

// Named this way to avoid conflicts with Guava
public final class FCollections {

    private FCollections() {
    }

    /**
     * Creates a new list that merges multiple collections.
     * This does NOT mutate any of the given collections.
     *
     * @param collections the collections
     * @param <T>         the type of the element of the collection
     * @param <Z>         the type of collection
     * @return the merged list
     */
    @SafeVarargs
    public static <T, Z extends Collection<T>> List<T> merge(Z... collections) {
        List<T> result = new ArrayList<>();

        for (Z collection : collections) {
            result.addAll(collection);
        }

        return result;
    }

    /**
     * Adds an array of elements to a collection.
     *
     * @param collection the collection
     * @param elements   the array of elements
     * @param <T>        the type of the element
     * @param <Z>        the type of the collection
     * @return the merged collection
     */
    @SafeVarargs
    public static <T, Z extends Collection<T>> Z addAll(Z collection, T... elements) {
        collection.addAll(Arrays.asList(elements));

        return collection;
    }

}
