package PAI.dto.degreeType;

import PAI.VOs.MaxEcts;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterDegreeTypeCommandTest {

    @Test
    void shouldCreateCommandCorrectlyWithMocks() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Mestrado");

        MaxEcts maxEcts = mock(MaxEcts.class);
        when(maxEcts.getMaxEcts()).thenReturn(120);

        // Act
        RegisterDegreeTypeCommand command = new RegisterDegreeTypeCommand(name, maxEcts);

        // Assert
        assertEquals(name, command.name());
        assertEquals(maxEcts, command.maxEcts());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        MaxEcts maxEcts = mock(MaxEcts.class);
        when(maxEcts.getMaxEcts()).thenReturn(60);

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RegisterDegreeTypeCommand(null, maxEcts));

        assertEquals("Name is required.", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenMaxEctsIsNull() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Licenciatura");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                new RegisterDegreeTypeCommand(name, null));

        assertEquals("Max ECTS is required.", exception.getMessage());
    }
}