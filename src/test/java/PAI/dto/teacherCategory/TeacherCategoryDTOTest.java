package PAI.dto.teacherCategory;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryDTOTest {

    @Test
    void shouldCreateTeacherCategoryResponseDTOCorrectly() {
        // Arrange
        String id = UUID.randomUUID().toString();
        String name = "Assistant Professor";

        // Act
        TeacherCategoryDTO teacherCategoryDTO = new TeacherCategoryDTO (id, name);

        // Assert
        assertEquals(id, teacherCategoryDTO.id());
        assertEquals(name, teacherCategoryDTO.name());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange
        String id = null;
        String name = null;

        // Act
        TeacherCategoryDTO teacherCategoryDTO = new TeacherCategoryDTO (id, name);

        // Assert
        assertEquals(null, teacherCategoryDTO.id());
        assertEquals(null, teacherCategoryDTO.name());
    }
  
}