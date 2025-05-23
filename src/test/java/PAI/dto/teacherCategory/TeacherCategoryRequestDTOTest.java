package PAI.dto.teacherCategory;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class TeacherCategoryRequestDTOTest {

    @Test
    void shouldCreateTeacherCategoryRequestDTOCorrectly() {
        // Arrange
        String name = "Assistant Professor";

        // Act
        TeacherCategoryRequestDTO teacherCategoryRequestDTO = new TeacherCategoryRequestDTO (name);

        // Assert
        assertEquals(name, teacherCategoryRequestDTO.name());
    }

    @Test
    void shouldHandleNullId() {
        // Arrange
        String name = null;

        // Act
        TeacherCategoryRequestDTO teacherCategoryRequestDTO = new TeacherCategoryRequestDTO (name);

        // Assert
        assertEquals(null, teacherCategoryRequestDTO.name());
    }
}