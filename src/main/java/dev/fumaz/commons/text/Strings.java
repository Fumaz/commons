package dev.fumaz.commons.text;

import org.jetbrains.annotations.Nullable;

public class Strings {

    public static boolean isBlank(@Nullable String string) {
        return string == null || string.chars().allMatch(Character::isWhitespace);
    }

    public static String capitalizeFully(@Nullable String string) {
        if (string == null || isBlank(string)) {
            return string;
        }

        String[] words = string.split(" ");
        StringBuilder builder = new StringBuilder();

        for (String word : words) {
            builder.append(Character.toUpperCase(word.charAt(0)))
                    .append(word.substring(1))
                    .append(" ");
        }

        return builder.toString().trim();
    }

}
