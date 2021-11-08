package dev.fumaz.commons.math;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RomansTest {

    @Test
    public void toRoman() {
        assertEquals("I", Romans.toRoman(1));
        assertEquals("II", Romans.toRoman(2));
        assertEquals("III", Romans.toRoman(3));
        assertEquals("IV", Romans.toRoman(4));
        assertEquals("V", Romans.toRoman(5));
        assertEquals("VI", Romans.toRoman(6));
        assertEquals("VII", Romans.toRoman(7));
        assertEquals("VIII", Romans.toRoman(8));
        assertEquals("IX", Romans.toRoman(9));
        assertEquals("X", Romans.toRoman(10));
        assertEquals("XI", Romans.toRoman(11));
        assertEquals("XII", Romans.toRoman(12));
        assertEquals("XIII", Romans.toRoman(13));
        assertEquals("XIV", Romans.toRoman(14));
        assertEquals("XV", Romans.toRoman(15));
        assertEquals("XVI", Romans.toRoman(16));
        assertEquals("XVII", Romans.toRoman(17));
        assertEquals("XVIII", Romans.toRoman(18));
        assertEquals("XIX", Romans.toRoman(19));
        assertEquals("XX", Romans.toRoman(20));
        assertEquals("XXI", Romans.toRoman(21));
        assertEquals("XXII", Romans.toRoman(22));
        assertEquals("XXIII", Romans.toRoman(23));
        assertEquals("XXIV", Romans.toRoman(24));
        assertEquals("XXV", Romans.toRoman(25));
        assertEquals("XXVI", Romans.toRoman(26));
        assertEquals("XXVII", Romans.toRoman(27));
        assertEquals("XXVIII", Romans.toRoman(28));
        assertEquals("XXIX", Romans.toRoman(29));
        assertEquals("XXX", Romans.toRoman(30));
        assertEquals("XXXI", Romans.toRoman(31));
        assertEquals("XXXII", Romans.toRoman(32));
        assertEquals("XXXIII", Romans.toRoman(33));
        assertEquals("XXXIV", Romans.toRoman(34));
        assertEquals("XXXV", Romans.toRoman(35));
        assertEquals("XXXVI", Romans.toRoman(36));
        assertEquals("XXXVII", Romans.toRoman(37));
        assertEquals("XXXVIII", Romans.toRoman(38));
        assertEquals("XXXIX", Romans.toRoman(39));
        assertEquals("XL", Romans.toRoman(40));
        assertEquals("XLI", Romans.toRoman(41));
        assertEquals("XLII", Romans.toRoman(42));
        assertEquals("XLIII", Romans.toRoman(43));
        assertEquals("XLIV", Romans.toRoman(44));
        assertEquals("XLV", Romans.toRoman(45));
        assertEquals("XLVI", Romans.toRoman(46));
        assertEquals("XLVII", Romans.toRoman(47));
        assertEquals("XLVIII", Romans.toRoman(48));
        assertEquals("XLIX", Romans.toRoman(49));
        assertEquals("L", Romans.toRoman(50));
        assertEquals("LI", Romans.toRoman(51));
        assertEquals("LII", Romans.toRoman(52));
        assertEquals("LIII", Romans.toRoman(53));
        assertEquals("LIV", Romans.toRoman(54));
        assertEquals("LV", Romans.toRoman(55));
    }

}