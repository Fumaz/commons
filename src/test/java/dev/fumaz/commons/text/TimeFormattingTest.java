package dev.fumaz.commons.text;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormattingTest {

    @Test
    void formatSeconds() {
        // given
        long seconds = 0;
        // when
        String formatted = TimeFormatting.formatSeconds(seconds);
        // then
        assertEquals("00:00", formatted);

        // given 67 seconds
        seconds = 67;
        // when
        formatted = TimeFormatting.formatSeconds(seconds);
        // then
        assertEquals("01:07", formatted);

        // given 105 seconds
        seconds = 105;
        // when
        formatted = TimeFormatting.formatSeconds(seconds);
        // then
        assertEquals("01:45", formatted);
    }

}