package dev.fumaz.commons.math;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Bytes {

    private Bytes() {
    }

    /**
     * Returns a human representation of a size in bytes
     *
     * @param bytes the size in bytes
     * @return the human readable string
     * @see <a href="https://stackoverflow.com/a/3758880">https://stackoverflow.com/a/3758880</a>
     */
    public static String toHumanReadableSize(long bytes) {
        long absoluteBytes = bytes == Long.MIN_VALUE ? Long.MAX_VALUE : Math.abs(bytes);

        if (absoluteBytes < 1024) {
            return bytes + " B";
        }

        long value = absoluteBytes;
        CharacterIterator iterator = new StringCharacterIterator("KMGTPE");

        for (int i = 40; i >= 0 && absoluteBytes > 0xfffccccccccccccL >> i; i -= 10) {
            value >>= 10;
            iterator.next();
        }

        value *= Long.signum(bytes);

        return String.format("%.1f %cB", value / 1024.0, iterator.current());
    }

}
