package dev.fumaz.commons.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecimalsTest {

    @Test
    void getDecimalPlaces() {
        // Given
        double value = 0.123456789;

        // When
        int decimalPlaces = Decimals.getDecimalPlaces(value);

        // Then
        assertEquals(9, decimalPlaces);
    }

}