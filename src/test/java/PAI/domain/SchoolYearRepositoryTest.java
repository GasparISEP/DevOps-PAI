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

    @Test
    void shouldReturnTheMostRecentSchoolYear() throws Exception {
        // Arrange
        SchoolYearRepository syr = new SchoolYearRepository();
        syr.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        syr.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2026");
        SchoolYear sy1 = new SchoolYear("Ano letivo", "01-09-2024", "30-06-2026");

        // Act
        SchoolYear sy2 = syr.getLatestSchoolYear();

        // Assert
        assertTrue(sy1.isSameSchoolYear(sy2));
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryIsEmpty() throws Exception {
        // Arrange
        SchoolYearRepository syr = new SchoolYearRepository();

        // Act
        SchoolYear sy1 = syr.getLatestSchoolYear();

        // Assert
        assertNull(sy1);
    }

    //Testing schoolYearExists method
    @Test
    void shouldReturnTrueWhenSchoolYearExists()throws Exception {
        // Arrange
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        SchoolYearRepository repository = new SchoolYearRepository();
        repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        repository.addSchoolYear("Ano letivo", "01-09-2023", "30-06-2024");

        // Act
        boolean result = repository.schoolYearExists(schoolYear1);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearDoesNotExist() throws Exception{
        // Arrange
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        SchoolYearRepository repository = new SchoolYearRepository();
        repository.addSchoolYear("Ano letivo", "01-09-2021", "30-06-2022");
        repository.addSchoolYear("Ano letivo", "01-09-2023", "30-06-2024");

        // Act
        boolean result = repository.schoolYearExists(schoolYear1);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenRepositoryIsEmpty()throws Exception {
        // Arrange
        SchoolYear schoolYear1 = new SchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        SchoolYearRepository repository = new SchoolYearRepository();

        // Act
        boolean result = repository.schoolYearExists(schoolYear1);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearIsNull() throws Exception{
        // Arrange
        SchoolYear schoolYear1 = null;
        SchoolYearRepository repository = new SchoolYearRepository();
        repository.addSchoolYear("Ano letivo", "01-09-2024", "30-06-2025");
        repository.addSchoolYear("Ano letivo", "01-09-2023", "30-06-2024");

        // Act
        boolean result = repository.schoolYearExists(schoolYear1);


        // Assert
        assertFalse(result);
    }
}