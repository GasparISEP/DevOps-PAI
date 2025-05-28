package PAI.dto.accessMethod;

import PAI.VOs.NameWithNumbersAndSpecialChars;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAccessMethodCommandTest {

    @Test
    void shouldCreateRegisterAccessMethodCommandCorrectly() {
        // Arrange
        String expectedName = "Special Access Method";
        NameWithNumbersAndSpecialChars nameVO = new NameWithNumbersAndSpecialChars(expectedName);

        // Act
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(nameVO);

        // Assert
        assertEquals(expectedName, command.name().getNameWithNumbersAndSpecialChars());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new NameWithNumbersAndSpecialChars(null);
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new NameWithNumbersAndSpecialChars("   ");
        });
        assertEquals("Name cannot be null or empty", exception.getMessage());
    }
}

