package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherWorkingPercentageResponseDTOTest {
    @Test
    void shouldCreateConstructor(){
        //arrange
        String teacherCareerProgressionID = "123456789";
        String date = "20-02-2024";
        String teacherID = "ABC";
        String teacherCategory = "1234567";
        int workingPercentage = 80;

        UpdateTeacherWorkingPercentageResponseDTO updateTeacherWorkingPercentageResponseDTO = new UpdateTeacherWorkingPercentageResponseDTO(teacherCareerProgressionID,date,teacherID,teacherCategory,workingPercentage);

        //assert
        assertNotNull(updateTeacherWorkingPercentageResponseDTO);
    }

}