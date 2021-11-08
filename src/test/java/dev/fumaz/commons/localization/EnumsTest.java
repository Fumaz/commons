package dev.fumaz.commons.localization;

import static org.junit.jupiter.api.Assertions.*;

class EnumsTest {

    // test Enums.getDisplayName()
    @org.junit.jupiter.api.Test
    void getDisplayName() {
        assertEquals("A", Enums.getDisplayName(TestEnum.A));
        assertEquals("B", Enums.getDisplayName(TestEnum.B));
        assertEquals("C", Enums.getDisplayName(TestEnum.C));
    }

    // test Enums.hasSensitiveValue()
    @org.junit.jupiter.api.Test
    void hasSensitiveValue() {
        assertTrue(Enums.hasSensitiveValue(TestEnum.class, "A"));
        assertTrue(Enums.hasSensitiveValue(TestEnum.class, "B"));
        assertFalse(Enums.hasSensitiveValue(TestEnum.class, "c"));
    }

    // test Enums.hasInsensitiveValue()
    @org.junit.jupiter.api.Test
    void hasInsensitiveValue() {
        assertTrue(Enums.hasInsensitiveValue(TestEnum.class, "a"));
        assertTrue(Enums.hasInsensitiveValue(TestEnum.class, "B"));
        assertTrue(Enums.hasInsensitiveValue(TestEnum.class, "c"));
    }

    // test Enums.toList()
    @org.junit.jupiter.api.Test
    void toList() {
        assertEquals(3, Enums.toList(TestEnum.class).size());
    }

    // test Enums.getSensitiveValue()
    @org.junit.jupiter.api.Test
    void getSensitiveValue() {
        assertEquals(TestEnum.A, Enums.getSensitiveValue(TestEnum.class, "A"));
        assertEquals(TestEnum.B, Enums.getSensitiveValue(TestEnum.class, "B"));
        assertNull(Enums.getSensitiveValue(TestEnum.class, "c"));
    }

    // test Enums.getInsensitiveValue()
    @org.junit.jupiter.api.Test
    void getInsensitiveValue() {
        assertEquals(TestEnum.A, Enums.getInsensitiveValue(TestEnum.class, "a"));
        assertEquals(TestEnum.B, Enums.getInsensitiveValue(TestEnum.class, "B"));
        assertEquals(TestEnum.C, Enums.getInsensitiveValue(TestEnum.class, "c"));
    }

    private enum TestEnum {
        A,
        B,
        C
    }

}