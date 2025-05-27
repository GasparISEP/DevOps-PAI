package PAI.assembler.totalEnrolledStudentsInProgrammesByDepartmentAndSchoolYear;


import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsCommand;
import PAI.dto.totalEnrolledStudents.TotalEnrolledStudentsRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TotalEnrolledStudentsAssemblerImplTest {

    @Test
    void shouldReturnAValidCommandWhenConvertFromRequest() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);

        String depID = "DEI";
        String syID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";

        when(request.departmentID()).thenReturn(depID);
        when(request.schoolYearID()).thenReturn(syID);

        // Act
        TotalEnrolledStudentsCommand command = totalEnrolledStudentsAssembler.fromRequestToCommand(request);

        // Assert
        assertNotNull(command);
        assertEquals(depID, command.departmentID().getAcronym().getAcronym());
        assertEquals(syID, command.schoolYearID().getSchoolYearID().toString());
    }

    @Test
    void shouldThrowExceptionIfRequestReceivedIsNull() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        TotalEnrolledStudentsRequest request = null;

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> totalEnrolledStudentsAssembler.fromRequestToCommand(request));

        // Assert
        assertNotNull(exception);
        assertEquals("TotalEnrolledStudentsRequest cannot be null", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionIfCommandContainsAnInvalidSchoolYearID() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);

        String depID = "DEI";
        String syID = "b8f8e7c6";

        when(request.departmentID()).thenReturn(depID);
        when(request.schoolYearID()).thenReturn(syID);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> totalEnrolledStudentsAssembler.fromRequestToCommand(request));

        // Assert
        assertNotNull(exception);
        assertTrue(exception.getMessage().contains("Invalid UUID string"));
        verify(request, times(1)).departmentID();
        verify(request, times(1)).schoolYearID();
        verifyNoMoreInteractions(request);
    }

    @Test
    void shouldThrowExceptionIfCommandContainsAnInvalidDepartmentID() {
        // Arrange
        ITotalEnrolledStudentsAssembler totalEnrolledStudentsAssembler = new TotalEnrolledStudentsAssemblerImpl();
        TotalEnrolledStudentsRequest request = mock(TotalEnrolledStudentsRequest.class);

        String depID = "DEItar";
        String syID = "b8f8e7c6-3a2d-4d99-9f84-32f4a1e1a7ab";

        when(request.departmentID()).thenReturn(depID);
        when(request.schoolYearID()).thenReturn(syID);

        // Act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> totalEnrolledStudentsAssembler.fromRequestToCommand(request));

        // Assert
        assertNotNull(exception);
        assertEquals("Acronym must contain only capital letters.", exception.getMessage());
        verify(request, times(1)).departmentID();
        verify(request, times(1)).schoolYearID();
        verifyNoMoreInteractions(request);
    }
}