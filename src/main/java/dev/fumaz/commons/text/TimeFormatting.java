package dev.fumaz.commons.text;

import org.jetbrains.annotations.NotNull;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

public class TimeFormatting {

    @NotNull
    public static String formatSeconds(long time) {
        boolean negative = time < 0;
        long absoluteTime = Math.abs(time);
        String pattern = "mm:ss";

        if (absoluteTime >= TimeUnit.HOURS.toSeconds(1)) {
            pattern = "hh:" + pattern;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault());
        Instant instant = Instant.ofEpochSecond(time);

        return formatter.format(instant);
    }

}
