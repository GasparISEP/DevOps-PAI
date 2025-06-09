package PAI.dto.teacherCareerProgression;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class UpdateTeacherCategoryDTOTest {

    @Test
    void shouldCreateUpdateTeacherCategoryDTOCorrectly() {
        // Arrange
        String date = "2025-06-01";
        String teacherID = "AAA";
        String teacherCategoryID = UUID.randomUUID().toString();
        int workingPercentage = 95;

        // Act
        UpdateTeacherCategoryDTO updateTeacherCategoryDTO = new UpdateTeacherCategoryDTO (date, teacherID, teacherCategoryID, workingPercentage);

        // Assert
        assertEquals(date, updateTeacherCategoryDTO.date());
        assertEquals(teacherID, updateTeacherCategoryDTO.teacherID());
        assertEquals(teacherCategoryID, updateTeacherCategoryDTO.teacherCategoryID());
        assertEquals(workingPercentage, updateTeacherCategoryDTO.workingPercentage());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange

        // Act
        UpdateTeacherCategoryDTO updateTeacherCategoryDTO = new UpdateTeacherCategoryDTO (null, null, null, 0);

        // Assert
        assertNull (updateTeacherCategoryDTO.teacherID());
        assertNull (updateTeacherCategoryDTO.teacherCategoryID());
        assertNull (updateTeacherCategoryDTO.date());
    }

}