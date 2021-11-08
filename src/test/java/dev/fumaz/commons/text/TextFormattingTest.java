package dev.fumaz.commons.text;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TextFormattingTest {

    @Test
    void prefix() {
        // given a list of strings
        List<String> strings = Arrays.asList("a", "b", "c");

        // when I call prefix on the arraylist
        List<String> prefixed = TextFormatting.prefix("prefix", strings);

        // then the arraylist should be prefixed
        assertEquals("prefixa", prefixed.get(0));
        assertEquals("prefixb", prefixed.get(1));
        assertEquals("prefixc", prefixed.get(2));
    }

}