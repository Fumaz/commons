package dev.fumaz.commons.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathsTest {

    @Test
    void parseIntOrNull() {
        // given a string
        String s = "123";
        // when parse it
        Integer result = Maths.parseIntOrNull(s);
        // then it should be parsed
        assertEquals(123, result);

        // given a non numerical string
        s = "abc";
        // when parse it
        result = Maths.parseIntOrNull(s);
        // then it should be null
        assertNull(result);
    }

    @Test
    void parseLongOrNull() {
        // given a string
        String s = "123";
        // when parse it
        Long result = Maths.parseLongOrNull(s);
        // then it should be parsed
        assertEquals(123L, result);

        // given a non numerical string
        s = "abc";
        // when parse it
        result = Maths.parseLongOrNull(s);
        // then it should be null
        assertNull(result);
    }

    @Test
    void parseIntOrDefault() {
        // given a string
        String s = "123";
        // when parse it
        Integer result = Maths.parseIntOrDefault(s, 0);
        // then it should be parsed
        assertEquals(123, result);

        // given a non numerical string
        s = "abc";
        // when parse it
        result = Maths.parseIntOrDefault(s, 0);
        // then it should be default
        assertEquals(0, result);
    }

    @Test
    void parseLongOrDefault() {
        // given a string
        String s = "123";
        // when parse it
        Long result = Maths.parseLongOrDefault(s, 0L);
        // then it should be parsed
        assertEquals(123L, result);

        // given a non numerical string
        s = "abc";
        // when parse it
        result = Maths.parseLongOrDefault(s, 0L);
        // then it should be default
        assertEquals(0L, result);
    }

}