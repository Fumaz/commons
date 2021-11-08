package dev.fumaz.commons.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BytesTest {

    @Test
    void toHumanReadableSize() {
        assertEquals("0 B", Bytes.toHumanReadableSize(0));
        assertEquals("1 B", Bytes.toHumanReadableSize(1));
        assertEquals("1.0 KB", Bytes.toHumanReadableSize(1024));
        assertEquals("1.0 MB", Bytes.toHumanReadableSize(1024 * 1024));
        assertEquals("1.0 GB", Bytes.toHumanReadableSize(1024 * 1024 * 1024));
        assertEquals("1.0 TB", Bytes.toHumanReadableSize(1024L * 1024 * 1024 * 1024));
        assertEquals("1.0 PB", Bytes.toHumanReadableSize(1024L * 1024 * 1024 * 1024 * 1024));
        assertEquals("1.0 EB", Bytes.toHumanReadableSize(1024L * 1024 * 1024 * 1024 * 1024 * 1024));
    }

}