package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;


import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TotalEnrolledStudentsAssemblerImplTest {

    @Test
    void shouldReturnAValidCommandWhenConvertFromRequest() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();

        String depID = "DEI";
        String syID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";

        // Act
        TotalEnrolledStudentsCommand command = totalEnrolledStudentsAssembler.fromRequestToCommand(depID, syID);

        // Assert
        assertNotNull(command);
        assertEquals(depID, command.departmentID().getAcronym().getAcronym());
        assertEquals(syID, command.schoolYearID().getSchoolYearID().toString());
    }

    @Test
    void shouldThrowExceptionIfDepartmentIdReceivedIsNull() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        String depID = null;
        String syID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> totalEnrolledStudentsAssembler.fromRequestToCommand(depID, syID));

        // Assert
        assertNotNull(exception);
        assertEquals("departmentID cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfSchoolYearIdReceivedIsNull() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        String depID = "AAA";
        String syID = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> totalEnrolledStudentsAssembler.fromRequestToCommand(depID, syID));

        // Assert
        assertNotNull(exception);
        assertEquals("schoolYearID cannot be null", exception.getMessage());
    }
}