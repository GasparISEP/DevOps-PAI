package PAI.dto.courseEdition;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefineRucResponseDTOTest {

    @Test
    void shouldCreateInstanceWithGivenValues() {
        // Arrange
        String teacherID = "AAB";
        SelectedCourseEditionIdDTO courseEditionDTO = new SelectedCourseEditionIdDTO(
                "Data Science", "DSD", java.util.UUID.randomUUID(),
                "ARIT", "Arithmancy", java.time.LocalDate.of(2023, 7, 1));
        // Act
        DefineRucResponseDTO dto = new DefineRucResponseDTO(teacherID, courseEditionDTO);
        // Assert
        assertEquals(teacherID, dto.teacherID());
        assertEquals(courseEditionDTO, dto.courseEditionDTO());
    }
}