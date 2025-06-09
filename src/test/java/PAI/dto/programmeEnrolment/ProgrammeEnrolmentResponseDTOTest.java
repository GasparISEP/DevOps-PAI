package PAI.dto.programmeEnrolment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentResponseDTOTest {

    @Test
    void shouldCreateValidDTO() {
        // Arrange
        UUID gid = UUID.randomUUID();
        int studentId = 1234567;
        String accessMethodName = "National Access";
        String programmeName = "Licenciatura em Engenharia Informática";
        LocalDate date = mock(LocalDate.class);

        // Act
        ProgrammeEnrolmentResponseDTO dto = new ProgrammeEnrolmentResponseDTO(
                gid, studentId, accessMethodName, programmeName, date
        );

        // Assert
        assertNotNull(dto);
        assertEquals(gid, dto.getProgrammeEnrolmentGID());
        assertEquals(studentId, dto.getStudentID());
        assertEquals(accessMethodName, dto.getAccessMethodID());
        assertEquals(programmeName, dto.getProgrammeID());
        assertEquals(date, dto.getDate());
    }
}
