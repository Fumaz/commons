package dev.fumaz.commons.localization;

import com.google.common.collect.ImmutableList;
import dev.fumaz.commons.text.Strings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Enums {

    private Enums() {
    }

    public static String getDisplayName(Enum<?> object) {
        return Strings.capitalizeFully(object.name().replace("_", " "));
    }

    public static <T extends Enum<T>> boolean hasSensitiveValue(Class<T> enumType, String name) {
        return Enums.hasValue(enumType, name, true);
    }

    public static <T extends Enum<T>> boolean hasInsensitiveValue(Class<T> enumType, String name) {
        return Enums.hasValue(enumType, name, false);
    }

    @Nullable
    public static <T extends Enum<T>> T getSensitiveValue(Class<T> enumType, String name) {
        return Enums.getValue(enumType, name, true);
    }

    @Nullable
    public static <T extends Enum<T>> T getInsensitiveValue(Class<T> enumType, String name) {
        return Enums.getValue(enumType, name, false);
    }

    @NotNull
    @Unmodifiable
    public static <T extends Enum<T>> List<T> toList(Class<T> enumType) {
        return ImmutableList.copyOf(enumType.getEnumConstants());
    }

    @NotNull
    @Unmodifiable
    public static <T extends Enum<T>> List<T> toList(Class<T> enumType, List<T> excluding) {
        return toList(enumType)
                .stream()
                .filter(t -> !excluding.contains(t))
                .collect(Collectors.toList());
    }

    @NotNull
    @Unmodifiable
    @SafeVarargs
    public static <T extends Enum<T>> List<T> toList(Class<T> enumType, T... excluding) {
        return toList(enumType, Arrays.asList(excluding));
    }

    @NotNull
    @Unmodifiable
    public static <T extends Enum<T>> List<T> toList(Class<T> enumType, Predicate<T> excluding) {
        return toList(enumType)
                .stream()
                .filter(excluding)
                .collect(Collectors.toList());
    }

    @Nullable
    private static <T extends Enum<T>> T getValue(Class<T> enumType, String name, boolean caseSensitive) {
        return Arrays.stream(enumType.getEnumConstants())
                .filter(constant -> caseSensitive ? constant.name().equals(name) : constant.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    private static <T extends Enum<T>> boolean hasValue(Class<T> enumType, String name, boolean caseSensitive) {
        return getValue(enumType, name, caseSensitive) != null;
    }

}
