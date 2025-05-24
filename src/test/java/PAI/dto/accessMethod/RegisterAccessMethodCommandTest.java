package PAI.dto.accessMethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegisterAccessMethodCommandTest {

    @Test
    void shouldCreateRegisterAccessMethodCommandCorrectly() {
        // Arrange
        String expectedName = "Special Access Method";

        // Act
        RegisterAccessMethodCommand command = new RegisterAccessMethodCommand(expectedName);

        // Assert
        assertEquals(expectedName, command.name());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterAccessMethodCommand(null);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterAccessMethodCommand("   ");
        });
        assertEquals("Name is required", exception.getMessage());
    }
}

