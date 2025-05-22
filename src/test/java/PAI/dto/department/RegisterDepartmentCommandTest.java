package PAI.dto.department;


import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RegisterDepartmentCommandTest {

    @Test
    void shouldCreatRegisterDepartmentCommandCorrectly() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        // Act
        RegisterDepartmentCommand command = new RegisterDepartmentCommand(name, acronym);

        // Assert
        assertEquals(name, command.name());
        assertEquals(acronym, command.acronym());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterDepartmentCommand(null, acronym);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterDepartmentCommand(name, acronym);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsNull() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterDepartmentCommand(name, null);
        });
        assertEquals("Acronym is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterDepartmentCommand(name, acronym);
        });
        assertEquals("Acronym is required", exception.getMessage());
    }

}