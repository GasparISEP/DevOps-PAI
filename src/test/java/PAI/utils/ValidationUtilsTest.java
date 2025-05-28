package PAI.utils;

import org.junit.jupiter.api.Test;

import static PAI.utils.ValidationUtils.validateNotBlank;
import static org.junit.jupiter.api.Assertions.*;

class ValidationUtilsTest {

    @Test
    void notNull_shouldReturnValue_whenValueIsNotNull() {
        //Arrange
        String input = "test";

        //Act
        String result = ValidationUtils.validateNotNull(input, "input");

        //Assert
        assertEquals("test", result);
    }

    @Test
    void notNull_shouldThrowException_whenValueIsNull() {
        //Arrange

       //Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                ValidationUtils.validateNotNull(null, "myParam"));

        assertEquals("myParam cannot be null.", exception.getMessage());
    }

    @Test
    void shouldNotThrowWhenStringIsValid() {
        //Arrange

        //Act & Assert
        assertDoesNotThrow(() -> validateNotBlank("valid", "TestParam"));
    }

    @Test
    void shouldThrowWhenStringIsNull() {
        //Arrange

        //Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            validateNotBlank(null, "TestParam");
        });
        assertEquals("TestParam cannot be null or blank.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenStringIsEmpty() {
        //Arrange

        //Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            validateNotBlank("", "TestParam");
        });
        assertEquals("TestParam cannot be null or blank.", ex.getMessage());
    }

    @Test
    void shouldThrowWhenStringIsBlank() {
        //Arrange

        //Act & Assert
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
            validateNotBlank("   ", "TestParam");
        });
        assertEquals("TestParam cannot be null or blank.", ex.getMessage());
    }

}