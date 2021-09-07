package dev.fumaz.commons.localization;

import com.google.common.collect.ImmutableList;
import org.apache.commons.text.WordUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.Arrays;
import java.util.List;

/**
 * @author Fumaz
 * @version 1.0
 * @since 1.0
 */
public final class Enums {

    private Enums() {
    }

    public static String getDisplayName(Enum<?> object) {
        return WordUtils.capitalizeFully(object.name().replace("_", " "));
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
