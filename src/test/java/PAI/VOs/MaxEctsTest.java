package PAI.VOs;

import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class MaxEctsTest {

    @Test
    void shouldStoreMaxEctsValueCorrectly() {
        int expectedValue = 300;
        MaxEcts maxEcts = new MaxEcts(expectedValue);
        assertEquals(expectedValue, maxEcts.getMaxEcts(), "The Ects value was not correctly stored.");
    }

    @Test
    void shouldThrowExceptionWhenValueIsZeroOrNegative() {
        assertThrows(BusinessRuleViolationException.class, () -> new MaxEcts(0), "Should throw exception if value is 0.");
        assertThrows(BusinessRuleViolationException.class, () -> new MaxEcts(-1), "Should throw exception for negative Ects values.");
    }

    @Test
    void shouldThrowExceptionWhenValueExceeds300() {
        assertThrows(BusinessRuleViolationException.class, () -> new MaxEcts(301), "Should throw exception for values over 300.");
    }

    @Test
    void shouldAcceptValidValues() {
        assertDoesNotThrow(() -> new MaxEcts(1), "Should not throw exception for minimum valid value.");
        assertDoesNotThrow(() -> new MaxEcts(180), "Should not throw exception for an intermediate valid value.");
        assertDoesNotThrow(() -> new MaxEcts(300), "Should not throw exception for maximum valid value.");
    }
}