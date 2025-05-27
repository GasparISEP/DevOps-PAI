package PAI.dto.schoolYear;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CurrentSchoolYearDTOTest {

    final String id = "b97e64c0-482d-4b30-aab3-d493d315e856";
    final String description = "2024/2025";
    final LocalDate startDate = LocalDate.of(2024, 1, 1);
    final LocalDate endDate = LocalDate.of(2025, 12, 31);

    @Test
    void testConstructor() {
        // Arrange & Act
        CurrentSchoolYearDTO CurrentSchoolYearDTO = new CurrentSchoolYearDTO(id, description, startDate, endDate);
        //Assert
        assertNotNull(CurrentSchoolYearDTO);
    }

    @Test
    void testId() {
        // Arrange & Act
        CurrentSchoolYearDTO CurrentSchoolYearDTO = new CurrentSchoolYearDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(CurrentSchoolYearDTO.id(), this.id);
    }

    @Test
    void testDescription() {
        // Arrange & Act
        CurrentSchoolYearDTO CurrentSchoolYearDTO = new CurrentSchoolYearDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(CurrentSchoolYearDTO.description(), this.description);
    }

    @Test
    void testStartDate() {
        // Arrange & Act
        CurrentSchoolYearDTO CurrentSchoolYearDTO = new CurrentSchoolYearDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(CurrentSchoolYearDTO.startDate(), this.startDate);
    }

    @Test
    void testEndDate() {
        // Arrange & Act
        CurrentSchoolYearDTO CurrentSchoolYearDTO = new CurrentSchoolYearDTO(id, description, startDate, endDate);
        //Assert
        assertEquals(CurrentSchoolYearDTO.endDate(), this.endDate);
    }
}