package PAI.dto.teacherCareerProgression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class TeacherWorkingPercentageUpdateDTOTest {

    @Test
    void shouldCreateValidDTO(){
        // Arrange
        String date = "2023-10-01";
        int workingpercentage = 80;
        String teacherid = "TID123";

        // Act
        TeacherWorkingPercentageUpdateDTO dto = new TeacherWorkingPercentageUpdateDTO(date, workingpercentage, teacherid);

        // Assert
        assertNotNull(dto);

    }


}