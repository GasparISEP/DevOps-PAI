package PAI.dto.teacherCareerProgression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeacherCareerProgressionResponseDTOTest {

    @Test
    void shouldCreateValidDTO(){
        // Arrange
        String tcpid = "123e4567-e89b-12d3-a456-426614174000";
        String date = "2023-10-01";
        String tcid = "TCID123";
        int workingpercentage = 80;
        String teacherid = "TID123";

        // Act
        TeacherCareerProgressionResponseDTO dto = new TeacherCareerProgressionResponseDTO(tcpid, date, tcid, workingpercentage, teacherid);

        // Assert
        assertNotNull(dto);

    }


}