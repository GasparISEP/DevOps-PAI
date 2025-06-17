package PAI.VOs;

import PAI.dto.courseEditionEnrolment.CourseEditionEnrolmentMinimalDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CourseEditionEnrolmentMinimalDTOTest {

    @Test
    void shouldCreateValidMinimalDTO() {
        // Arrange
        String id = "8a274300-5ab1-44a9-bfa7-587a808a33f7";
        String name = "Engenharia de Software";

        // Act
        CourseEditionEnrolmentMinimalDTO dto = new CourseEditionEnrolmentMinimalDTO(id, name);

        // Assert
        assertEquals(id, dto.courseEditionID());
        assertEquals(name, dto.courseEditionName());
    }

    @Test
    void shouldAllowNullValues() {
        // Act
        CourseEditionEnrolmentMinimalDTO dto = new CourseEditionEnrolmentMinimalDTO(null, null);

        // Assert
        assertNull(dto.courseEditionID());
        assertNull(dto.courseEditionName());
    }
}