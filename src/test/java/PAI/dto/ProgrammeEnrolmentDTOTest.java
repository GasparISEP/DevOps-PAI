package PAI.dto;

import PAI.dto.programmeEnrolment.ProgrammeEnrolmentDTO;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentDTOTest {

    @Test
    void shouldCreateValidDTO() {
        // Arrange
        int studentID = 1234567;
        String acessMethodID = "1";
        String programmeName = "1";
        String programmeAcronym = "LEI";
        LocalDate date = mock(LocalDate.class);


        // Act
        ProgrammeEnrolmentDTO programmeEnrolmentDTO = new ProgrammeEnrolmentDTO(studentID,acessMethodID,programmeName,programmeAcronym,date);

        // Assert
        assertNotNull(programmeEnrolmentDTO);
    }

    @Test
    void testGetters() {
        int studentID = 123;
        String accessMethodID = "AM123";
        String programmeName = "Computer Science";
        String programmeAcronym = "CS";
        LocalDate date = LocalDate.of(2025, 5, 20);

        ProgrammeEnrolmentDTO dto = new ProgrammeEnrolmentDTO(studentID, accessMethodID, programmeName, programmeAcronym, date);

        assertEquals(studentID, dto.getStudentID());
        assertEquals(accessMethodID, dto.getAccessMethodID());
        assertEquals(programmeName, dto.getProgrammeName());
        assertEquals(programmeAcronym, dto.getProgrammeAcronym());
        assertEquals(date, dto.getDate());
    }

}