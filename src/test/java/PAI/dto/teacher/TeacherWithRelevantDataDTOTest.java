package PAI.dto.teacher;

import PAI.dto.Teacher.TeacherWithRelevantDataDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TeacherWithRelevantDataDTOTest {

    @Test
    void testConstructorAssignsFieldsCorrectly() {
        //Arrange + Act
        TeacherWithRelevantDataDTO dto = new TeacherWithRelevantDataDTO("João Silva", "JS", "Auxiliar", 100);
        //Assert
        assertEquals("João Silva", dto.getName());
        assertEquals("JS", dto.getAcronym());
        assertEquals("Auxiliar", dto.getCategory());
        assertEquals(100, dto.getWorkingPercentage());
    }

}