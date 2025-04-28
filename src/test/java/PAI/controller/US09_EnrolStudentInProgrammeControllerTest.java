package PAI.controller;

import PAI.VOs.*;
import PAI.service.IProgrammeEnrolmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US09_EnrolStudentInProgrammeControllerTest {

    private IProgrammeEnrolmentService enrolmentService;
    private US09_EnrolStudentInProgrammeController controller;

    @BeforeEach
    void setUp() {
        enrolmentService = mock(IProgrammeEnrolmentService.class);
        controller = new US09_EnrolStudentInProgrammeController(enrolmentService);
    }

    @Test
    void enrolStudentInProgramme_SuccessfulEnrolment_ReturnsTrue() {
        // Arrange
        int studentNumber = 1234567;
        UUID accessMethodID = UUID.randomUUID();
        String programmeName = "Software Engineering";
        String acronym = "SE";
        String enrolmentDate = "01-09-2024";

        when(enrolmentService.enrolStudentInProgramme(
                any(StudentID.class),
                any(AccessMethodID.class),
                any(ProgrammeID.class),
                any(Date.class)
        )).thenReturn(true);

        // Act
        boolean result = controller.enrolStudentInProgramme(studentNumber, accessMethodID, programmeName, acronym, enrolmentDate);

        // Assert
        assertTrue(result);
        verify(enrolmentService, times(1)).enrolStudentInProgramme(any(), any(), any(), any());
    }

    @Test
    void enrolStudentInProgramme_UnsuccessfulEnrolment_ReturnsFalse() {
        // Arrange
        when(enrolmentService.enrolStudentInProgramme(any(), any(), any(), any()))
                .thenReturn(false);

        // Act
        boolean result = controller.enrolStudentInProgramme(1234567, UUID.randomUUID(), "Programme", "PR", "01-09-2024");

        // Assert
        assertFalse(result);
        verify(enrolmentService, times(1)).enrolStudentInProgramme(any(), any(), any(), any());
    }

    @Test
    void enrolStudentInProgramme_ExceptionThrown_ReturnsFalse() {
        // Arrange
        when(enrolmentService.enrolStudentInProgramme(any(), any(), any(), any()))
                .thenThrow(new RuntimeException("Database error"));

        // Act
        boolean result = controller.enrolStudentInProgramme(1234567, UUID.randomUUID(), "Programme", "PR", "01-09-2024");

        // Assert
        assertFalse(result);
        verify(enrolmentService, times(1)).enrolStudentInProgramme(any(), any(), any(), any());
    }

    @Test
    void constructor_NullService_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(null));
    }

}

