package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DefineRucResponseDTOTest {

    @Test
    void shouldCreateInstanceWithGivenValues() {
        // Arrange
        String teacherID = "AAB";
        UUID courseEditionDTO =  UUID.randomUUID();

        // Act
        DefineRucResponseDTO dto = new DefineRucResponseDTO(teacherID, courseEditionDTO);
        // Assert
        assertEquals(teacherID, dto.teacherID());
        assertEquals(courseEditionDTO, courseEditionDTO);
    }
}