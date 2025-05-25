package PAI.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void notNull_shouldReturnValue_whenValueIsNotNull() {
        String input = "test";
        String result = ValidationUtils.validateNotNull(input, "input");
        assertEquals("test", result);
    }

    @Test
    void notNull_shouldThrowException_whenValueIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                ValidationUtils.validateNotNull(null, "myParam"));

        assertEquals("myParam cannot be null.", exception.getMessage());
    }

}