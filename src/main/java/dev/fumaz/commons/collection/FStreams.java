package dev.fumaz.commons.collection;

import java.util.Collections;
import java.util.Enumeration;
import java.util.stream.Stream;

/**
 * A utility for {@link Stream}s
 *
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class FStreams {

    private FStreams() {
    }

    public static <T> Stream<T> stream(Enumeration<T> enumeration) {
        return Collections.list(enumeration).stream();
    }

}
