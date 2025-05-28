package PAI.dto.department;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DepartmentWithDirectorCommandTest {

    @Test
    void shouldCreateDepartmentWithDirectorCommandCorrectly() {
        // Arrange

        DepartmentID departmentID = mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");

        TeacherID  teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act
        DepartmentWithDirectorCommand command = new DepartmentWithDirectorCommand(departmentID, teacherID);

        // Assert
        assertEquals(departmentID, command.department());
        assertEquals( teacherID, command.director());

    }

    @Test
    void shouldThrowExceptionWhenNameOfDepartmentIsNull() {
        // Arrange
        DepartmentID departmentID= mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID  teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(null,  teacherID);
        });
        assertEquals("Department is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenIDOfDepartmentIsNull() {
        // Arrange
        DepartmentID departmentID= mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID  teacherID= mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(null,  teacherID);
        });
        assertEquals("Department is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenAcronymOfDepartmentIsBlank() {
        // Arrange
        DepartmentID departmentID= mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID  teacherID = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(null, teacherID);
        });
        assertEquals("Department is required", exception.getMessage());
    }
    //
    @Test
    void shouldThrowExceptionWhenTeacherIDIsBlank() {
        // Arrange
        DepartmentID departmentID= mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(departmentID,null);
        });
        assertEquals("Director is required", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenTeacherIDIsNull() {
        // Arrange
        // Arrange
        DepartmentID departmentID= mock(DepartmentID.class);
        DepartmentAcronym acronym = mock(DepartmentAcronym.class);
        when(acronym.getAcronym()).thenReturn("DEI");
        TeacherID teacher = mock(TeacherID.class);
        TeacherAcronym teacherAcronym = mock(TeacherAcronym.class);
        when(teacherAcronym.getAcronym()).thenReturn("MAN");
        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new DepartmentWithDirectorCommand(departmentID,null);
        });
        assertEquals("Director is required", exception.getMessage());
    }




}