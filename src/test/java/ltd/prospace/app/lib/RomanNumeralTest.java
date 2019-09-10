package ltd.prospace.app.lib;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RomanNumeralTest {

    @Test
    void romanToIntTest() {
        assertThrows(IllegalArgumentException.class, () -> RomanNumeral.romanToInt("MMMM"));
        assertEquals(1434, RomanNumeral.romanToInt("MCDXXXIV"));
    }

    @Test
    void intToRomanTest() {
        assertEquals("LXXIX", RomanNumeral.intToRoman(79));
    }
}