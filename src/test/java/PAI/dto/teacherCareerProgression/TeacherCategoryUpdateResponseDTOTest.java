package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryUpdateResponseDTOTest {
    @Test
    void shouldCreateConstructor(){
        //arrange
        String date = "20-02-2024";
        String teacherID = "ABC";
        String teacherCategory = "1234567";
        int workingPercentage = 80;

        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = new TeacherCategoryUpdateResponseDTO(date,teacherID,teacherCategory,workingPercentage);

        //assert
        assertNotNull(teacherCategoryUpdateResponseDTO);
    }

    @Test
    void shouldCreateEmptyConstructor(){
        TeacherCategoryUpdateResponseDTO teacherCategoryUpdateResponseDTO = new TeacherCategoryUpdateResponseDTO();
        //assert
        assertNotNull(teacherCategoryUpdateResponseDTO);
    }
}