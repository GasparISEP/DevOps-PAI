package PAI.dto.schoolYear;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

class CurrentSchoolYearResponseDTOTest {

    final String id = "b97e64c0-482d-4b30-aab3-d493d315e856";
    final String description = "2024/2025";
    final LocalDate startDate = LocalDate.of(2024, 1, 1);
    final LocalDate endDate = LocalDate.of(2025, 12, 31);

    @Test
    void testConstructor() {
        // Arrange & Act
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = new CurrentSchoolYearResponseDTO(id, description, startDate, endDate);
        //Assert
        assertNotNull(currentSchoolYearResponseDTO);
    }

    @Test
    void testId() {
        // Arrange & Act
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = new CurrentSchoolYearResponseDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(currentSchoolYearResponseDTO.id(), this.id);
    }

    @Test
    void testDescription() {
        // Arrange & Act
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = new CurrentSchoolYearResponseDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(currentSchoolYearResponseDTO.description(), this.description);
    }

    @Test
    void testStartDate() {
        // Arrange & Act
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = new CurrentSchoolYearResponseDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(currentSchoolYearResponseDTO.startDate(), this.startDate);
    }

    @Test
    void testEndDate() {
        // Arrange & Act
        CurrentSchoolYearResponseDTO currentSchoolYearResponseDTO = new CurrentSchoolYearResponseDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(currentSchoolYearResponseDTO.endDate(), this.endDate);
    }
}