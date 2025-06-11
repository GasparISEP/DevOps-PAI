package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherCategoryResponseDTOTest {

    @Test
    void shouldCreateConstructor(){
        //arrange
        String teacherCareerProgressionID = UUID.randomUUID().toString();
        String date = "20-02-2024";
        String teacherID = "ABC";
        String teacherCategory = UUID.randomUUID().toString();
        int workingPercentage = 80;

        UpdateTeacherCategoryResponseDTO updateTeacherCategoryResponseDTO =
                new UpdateTeacherCategoryResponseDTO
                        (teacherCareerProgressionID,date,teacherID,teacherCategory,workingPercentage);

        //assert
        assertNotNull(updateTeacherCategoryResponseDTO);
    }

}