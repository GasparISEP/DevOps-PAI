package PAI.controller;

import PAI.VOs.*;
import PAI.domain.programmeEnrolment.ProgrammeEnrolment;
import PAI.service.programmeEnrolment.IProgrammeEnrolmentService;
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
    void enrolStudentInProgramme_SuccessfulEnrolment_ReturnsTrue() throws Exception {
        // Arrange
        int    studentNumber   = 1234567;
        UUID   accessMethodID  = UUID.randomUUID();
        String progName        = "Software Engineering";
        String acronym         = "SE";
        String enrolDate       = "01-09-2024";

        ProgrammeEnrolment mockEnrolment = mock(ProgrammeEnrolment.class);
        when(enrolmentService.enrolStudentInProgramme(
                any(StudentID.class),
                any(AccessMethodID.class),
                any(ProgrammeID.class),
                any(Date.class)
        )).thenReturn(mockEnrolment);

        // Act
        ProgrammeEnrolment result = controller.enrolStudentInProgramme(
                studentNumber, accessMethodID, progName, acronym, enrolDate
        );

        // Assert
        assertSame(mockEnrolment, result);
        verify(enrolmentService, times(1))
                .enrolStudentInProgramme(any(), any(), any(), any());
    }

    @Test
    void enrolStudentInProgramme_UnsuccessfulEnrolment_ReturnsNull() throws Exception {
        // Arrange

        String validDate = "01-09-2024";
        when(enrolmentService.enrolStudentInProgramme(
                any(StudentID.class),
                any(AccessMethodID.class),
                any(ProgrammeID.class),
                any(Date.class)
        )).thenReturn(null);

        // Act
        ProgrammeEnrolment result = controller.enrolStudentInProgramme(
                1234567,
                UUID.randomUUID(),
                "Programme",
                "PR",
                validDate
        );

        // Assert
        assertNull(result);
        verify(enrolmentService, times(1))
                .enrolStudentInProgramme(any(), any(), any(), any());
    }

    @Test
    void enrolStudentInProgramme_ExceptionThrown_ReturnsNull() throws Exception {
        // Arrange
        String badDate = "01-13-2024";
        when(enrolmentService.enrolStudentInProgramme(any(), any(), any(), any()))
                .thenReturn(mock(ProgrammeEnrolment.class));

        // Act
        ProgrammeEnrolment result = controller.enrolStudentInProgramme(
                123, UUID.randomUUID(), "X", "X", badDate
        );

        // Assert
        assertNull(result);
    }

    @Test
    void constructor_NullService_ThrowsIllegalArgumentException() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> new US09_EnrolStudentInProgrammeController(null));
    }

}

