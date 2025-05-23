package PAI.dto.course;


import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CourseResponseDTOTest {

    @Test
    void shouldCreateCourseResponseDTOWithGivenValues() {
        // Arrange
        String name = "Software Engineering Department";
        String acronym = "DEI";

        // Act
        CourseResponseDTO response = new CourseResponseDTO(name, acronym);
        CourseResponseDTO expected = new CourseResponseDTO(response._acronym(), response._name());

        // Assert
        assertEquals(expected, response);
    }
}