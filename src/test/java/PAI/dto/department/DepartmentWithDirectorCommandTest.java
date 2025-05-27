package PAI.dto.department;

import PAI.VOs.DepartmentAcronym;
import PAI.VOs.Name;
import PAI.VOs.TeacherAcronym;
import PAI.VOs.TeacherID;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentWithDirectorCommandTest {

    @Test
    void shouldCreateDepartmentWithDirectorCommandCorrectly() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");

        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act
        DepartmentWithDirectorCommand command = new DepartmentWithDirectorCommand(name, acronym, teacher);

        // Assert
        assertEquals(name, command.name());
        assertEquals(acronym, command.acronym());
        assertEquals(teacher, command.director());

    }

    @Test
    void shouldThrowExceptionWhenNameOfDepartmentIsNull() {
        // Arrange
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(null, acronym, teacher);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameOfDepartmentIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new RegisterDepartmentRequestVOs(name, acronym);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymOfDepartmentIsNull() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(name, null, teacher);
        });
        assertEquals("Acronym is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymOfDepartmentIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(name, null, teacher);
        });
        assertEquals("Acronym is required", exception.getMessage());
    }
    //
    @Test
    void shouldThrowExceptionWhenNameOfTeacherIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(name, null, teacher);
        });
        assertEquals("Name is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymOfTeachertIsNull() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(name, acronym, null);
        });
        assertEquals("Director is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymOfTeacherIsBlank() {
        // Arrange
        Name name = mock(Name.class);
        when(name.getName()).thenReturn("Software Engineering Department");
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(name, acronym, null);
        });
        assertEquals("Director is required", exception.getMessage());
    }



}