package ltd.prospace.app.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {

    @Test
    void romanToIntTest() {
        assertEquals(3999, RomanNumeral.romanToInt("MMMCMXCIX"));
    }

    @Test
    void intToRomanTest() {
        assertEquals("LXXIX", RomanNumeral.intToRoman(79));
    }
}