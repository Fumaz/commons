package dev.fumaz.commons.text;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextFormatting {

    /**
     * Returns a list with every string preceded by a prefix
     *
     * @param prefix  the prefix
     * @param strings the list of strings
     * @return the prefixed list of strings
     */
    @NotNull
    public static List<String> prefix(String prefix, List<String> strings) {
        return strings
                .stream()
                .map(string -> prefix + string)
                .collect(Collectors.toList());
    }

    /**
     * Returns a list with every string preceded by a prefix
     *
     * @param prefix  the prefix
     * @param strings the list of strings
     * @return the prefixed list of strings
     */
    @NotNull
    public static List<String> prefix(String prefix, String... strings) {
        return prefix(prefix, Arrays.asList(strings));
    }

}
