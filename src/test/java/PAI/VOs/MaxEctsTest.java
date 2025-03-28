package PAI.VOs;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MaxEctsTest {

    @Test
    void shouldStoreMaxEctsValueCorrectly() {
        int expectedValue = 240;
        MaxEcts maxEcts = new MaxEcts(expectedValue);
        assertEquals(expectedValue, maxEcts.getMaxEcts(), "The Ects value was not correctly stored.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsZeroOrNegative() {
        assertThrows(IllegalArgumentException.class, () -> new MaxEcts(0), "Should throw exception if value is 0.");
        assertThrows(IllegalArgumentException.class, () -> new MaxEcts(-1), "Should throw exception for negative Ects values.");
    }

    @Test
    void shouldThrowExceptionWhenValueExceeds240() {
        assertThrows(IllegalArgumentException.class, () -> new MaxEcts(241), "Should throw exception for values over 240.");
    }

    @Test
    void shouldAcceptValidValues() {
        assertDoesNotThrow(() -> new MaxEcts(1), "Should not throw exception for minimum valid value.");
        assertDoesNotThrow(() -> new MaxEcts(120), "Should not throw exception for an intermidiate valid value.");
        assertDoesNotThrow(() -> new MaxEcts(240), "Should not throw exception for maximum valid value.");
    }
}