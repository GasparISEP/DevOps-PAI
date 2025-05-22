package PAI.dto.degreeType;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DegreeTypeDTOTest {

    @Test
    void shouldCreateDegreeTypeDTOWithCorrectData() {
        // Arrange & Act
        DegreeTypeDTO dto = new DegreeTypeDTO("1234-uuid", "Mestrado", 180);

        // Assert
        assertEquals("1234-uuid", dto.id());
        assertEquals("Mestrado", dto.name());
        assertEquals(180, dto.maxEcts());
    }

}