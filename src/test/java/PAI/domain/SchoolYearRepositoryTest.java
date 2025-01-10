package PAI.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SchoolYearRepositoryTest {

    // Valid addition

    @Test
    void createEmptyRepositorySuccessfully() {

        SchoolYearRepository repository = new SchoolYearRepository();
    }

    @Test
    void objectIsCreatedWithValidParameters() throws Exception {
        // Arrange
        SchoolYearRepository repository = new SchoolYearRepository();
        // Act
        SchoolYear newSchoolYear = new SchoolYear("Ano letivo de", "20-09-2024", "20-06-2025");
    }

    @Test
    void testAddValidSchoolYear() throws Exception {
        // Arrange
        SchoolYearRepository repository = new SchoolYearRepository();
        // Act
        boolean result = repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        // Assert
        assertTrue(result);
    }

    @Test
    void testAddSchoolYearsWithDifferentDatesSuccessufully() throws Exception {
        // Arrange
        SchoolYearRepository repository = new SchoolYearRepository();
        repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        repository.addSchoolYear("Ano letivo", "07-09-2024", "20-06-2025");
        // Act & Assert
        assertThrows(Exception.class, () -> repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025"));
    }

    // Invalid addition

    @Test
    void testAddSchoolYearsWithSameDatesThrowsException() throws Exception {
        // Arrange
        SchoolYearRepository repository = new SchoolYearRepository();
        repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        // Act & Assert
        assertThrows(Exception.class, () -> repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025"));
    }
}