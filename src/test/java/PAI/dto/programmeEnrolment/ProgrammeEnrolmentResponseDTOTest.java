package PAI.dto.programmeEnrolment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class ProgrammeEnrolmentResponseDTOTest {

    @Test
    void shouldCreateValidDTO() {
        // Arrange
        String studentName = "João";
        String acessMethodName = "National Acess";
        String programmeName = "Licenciatura em Engenharia Informática";
        LocalDate date = mock(LocalDate.class);


        // Act
        ProgrammeEnrolmentResponseDTO programmeEnrolmentResponseDTO = new ProgrammeEnrolmentResponseDTO(studentName,acessMethodName,programmeName,date);

        // Assert
        assertNotNull(programmeEnrolmentResponseDTO);
    }

}