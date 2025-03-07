package PAI.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearRepositoryTest {

    // Valid addition

    @Test
    void createEmptyRepositorySuccessfully() {
        //arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);

        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
    }

    @Test
    void testAddValidSchoolYear() throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);

        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble);

        // Act
        boolean result = repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        // Assert
        assertTrue(result);
    }

    @Test
    void testAddSchoolYearsWithDifferentDatesSuccessufully() throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        // Act
        boolean result1 = repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        boolean result2 = repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        // Assert
        assertTrue(result1);
        assertTrue(result2);
    }

    // Invalid addition

    @Test
    void testAddSchoolYearsWithSameDatesThrowsException() throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble2);

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(true);

        // Act & Assert
        assertThrows(Exception.class, () -> repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"));
    }

    @Test
    void shouldReturnTheCurrentSchoolYearWhenThereAreMultipleYearsAheadInTheRepository() throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        when(schoolYearDouble1.getStartDate()).thenReturn(LocalDate.of(2023, 9, 1));
        when(schoolYearDouble1.getEndDate()).thenReturn(LocalDate.of(2024, 8, 31));
        when(schoolYearDouble2.getStartDate()).thenReturn(LocalDate.of(2024, 9, 1));
        when(schoolYearDouble2.getEndDate()).thenReturn(LocalDate.of(2025, 8, 31));

        // Act
        SchoolYear sy1 = repository.getCurrentSchoolYear();

        // Assert
        assertEquals(sy1, schoolYearDouble2);
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryDoesNotHaveCurrentSchoolYear() throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 26/27", "01-09-2025", "31-08-2026"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear("School Year 26/27", "01-09-2025", "31-08-2026");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");
        when(schoolYearDouble1.getStartDate()).thenReturn(LocalDate.of(2026, 9, 1));
        when(schoolYearDouble1.getEndDate()).thenReturn(LocalDate.of(2027, 8, 31));
        when(schoolYearDouble2.getStartDate()).thenReturn(LocalDate.of(2023, 9, 1));
        when(schoolYearDouble2.getEndDate()).thenReturn(LocalDate.of(2024, 8, 31));

        // Act
        SchoolYear currentSchoolYear = repository.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryIsEmpty() {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);

        // Act
        SchoolYear currentSchoolYear = repository.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
    }

    //Testing schoolYearExists method
    @Test
    void shouldReturnTrueWhenSchoolYearExists()throws Exception {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(true);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearDoesNotExist() throws Exception{
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(false);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenRepositoryIsEmpty() {
        // Arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearIsNull() {
        // Arrange
        SchoolYear schoolYear = null;
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);

        // Act
        boolean result = repository.schoolYearExists(schoolYear);


        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheListOfSchoolYears() throws Exception {
        //arrange
        SchoolYearFactory schoolYearFactoryDouble = mock(SchoolYearFactory.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        //act
        List<SchoolYear> result = repository.getAllSchoolYears();

        //assert
        assertEquals(result.size(), 2);
    }
}