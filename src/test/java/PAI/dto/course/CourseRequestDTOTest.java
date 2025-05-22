package PAI.dto.course;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CourseRequestDTOTest {

    @Test
    void testEmptyConstructorNotNull () {
        // Arrange + Act
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO();

        // Assert
        assertNotNull(courseRequestDTO);
    }

    @Test
    void testConstructorNotNull () {
        // Arrange
        String courseId = "DSOFT; Desenvolvimento de Software";

        // Act
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO(courseId);

        // Assert
        assertNotNull(courseRequestDTO);
    }

    @Test
    void testGetCourseId () {
        // Arrange
        String courseId = "DSOFT; Desenvolvimento de Software";
        CourseRequestDTO courseRequestDTO = new CourseRequestDTO(courseId);

        // Act
        String result = courseRequestDTO.getCourseId();

        // Assert
        assertNotNull(courseId, result);
    }
}