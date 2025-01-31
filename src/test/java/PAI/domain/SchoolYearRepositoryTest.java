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
    void shouldReturnTheCurrentSchoolYearWhenThereAreMultipleYearsAheadInTheRepository() throws Exception {
        // Arrange
        SchoolYearRepository syr = new SchoolYearRepository();
        syr.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        syr.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");
        syr.addSchoolYear("School Year 25/26", "01-09-2025", "31-08-2026");
        syr.addSchoolYear("School Year 26/27", "01-09-2026", "31-08-2027");
        SchoolYear sy1 = new SchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        // Act
        SchoolYear sy2 = syr.getCurrentSchoolYear();

        // Assert
        assertTrue(sy1.isSameSchoolYear(sy2));
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryDoesNotHaveCurrentSchoolYear() throws Exception {
        // Arrange
        SchoolYearRepository syr = new SchoolYearRepository();
        syr.addSchoolYear("School Year 20/21", "01-09-2020", "31-08-2021");
        syr.addSchoolYear("School Year 21/22", "01-09-2021", "31-08-2022");
        syr.addSchoolYear("School Year 22/23", "01-09-2022", "31-08-2023");
        syr.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        syr.addSchoolYear("School Year 25/26", "01-09-2025", "31-08-2026");
        // Act
        SchoolYear currentSchoolYear = syr.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryIsEmpty() {
        // Arrange
        SchoolYearRepository syr = new SchoolYearRepository();

        // Act
        SchoolYear currentSchoolYear = syr.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
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