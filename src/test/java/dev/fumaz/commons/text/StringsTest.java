package dev.fumaz.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringsTest {

    @Test
    void isBlank() {
        // given a string containing only whitespaces
        String s = " \t\r\n";

        // when the string is checked
        boolean result = Strings.isBlank(s);

        // then the result is true
        assertTrue(result);

        // given a string containing any alphanumerical characters
        s = "abc123";

        // when the string is checked
        result = Strings.isBlank(s);

        // then the result is false
        assertFalse(result);
    }

    @Test
    void capitalizeFully() {
        // given a lowercase string
        String s = "abc";

        // when the string is capitalized
        String result = Strings.capitalizeFully(s);

        // then the result is "Abc"
        assertEquals("Abc", result);

        // given a uppercase string
        s = "ABC";

        // when the string is capitalized
        result = Strings.capitalizeFully(s);

        // then the result is "Abc"
        assertEquals("Abc", result);

        // given a mixed case string
        s = "AbC";

        // when the string is capitalized
        result = Strings.capitalizeFully(s);

        // then the result is "Abc"
        assertEquals("Abc", result);
    }

}