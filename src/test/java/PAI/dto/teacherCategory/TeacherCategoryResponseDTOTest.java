package PAI.dto.teacherCategory;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryResponseDTOTest {

    @Test
    void shouldCreateTeacherCategoryResponseDTOCorrectly() {
        // Arrange
        String id = UUID.randomUUID().toString();
        String name = "Assistant Professor";

        // Act
        TeacherCategoryResponseDTO teacherCategoryResponseDTO = new TeacherCategoryResponseDTO (id, name);

        // Assert
        assertEquals(id, teacherCategoryResponseDTO.id());
        assertEquals(name, teacherCategoryResponseDTO.name());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange
        String id = null;
        String name = null;

        // Act
        TeacherCategoryResponseDTO teacherCategoryResponseDTO = new TeacherCategoryResponseDTO (id, name);

        assertEquals(null, teacherCategoryResponseDTO.id());
        assertEquals(null, teacherCategoryResponseDTO.name());
    }
}
