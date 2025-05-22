package PAI.dto.course;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CourseResponseDTOTest {

    @Test
    public void testConstructorNotNull () {

        // Arrange
        String acronym = "SCOMRED";
        String name = "Sistemas de Computação e Redes";

        // Act
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(acronym, name);

        // Assert
        assertNotNull(courseResponseDTO);
    }

    @Test
    public void getAcronymShouldReturnAcronym() {

        // Arrange
        String acronym = "DSOFT";
        String name = "Desenvolvimento de Software";
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(acronym, name);

        // Act
        String result = courseResponseDTO.getAcronym();

        // Assert
        assertEquals(acronym, result);
    }

    @Test
    public void getNameShouldReturnAcronym () {

        // Arrange
        String acronym = "DSOFT";
        String name = "Desenvolvimento de Software";
        CourseResponseDTO courseResponseDTO = new CourseResponseDTO(acronym, name);

        // Act
        String result = courseResponseDTO.getName();

        // Assert
        assertEquals(name, result);
    }
}