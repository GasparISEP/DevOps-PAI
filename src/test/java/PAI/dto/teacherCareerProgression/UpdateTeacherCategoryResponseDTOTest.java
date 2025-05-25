package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherCategoryResponseDTOTest {
    @Test
    void shouldCreateConstructor(){
        //arrange
        String date = "20-02-2024";
        String teacherID = "ABC";
        String teacherCategory = "1234567";
        int workingPercentage = 80;

        UpdateTeacherCategoryResponseDTO updateTeacherCategoryResponseDTO = new UpdateTeacherCategoryResponseDTO(date,teacherID,teacherCategory,workingPercentage);

        //assert
        assertNotNull(updateTeacherCategoryResponseDTO);
    }

}