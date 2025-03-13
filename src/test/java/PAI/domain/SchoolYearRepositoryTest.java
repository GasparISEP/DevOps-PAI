package PAI.domain;

import PAI.factory.ProgrammeFactory;
import PAI.factory.ProgrammeRepositoryListFactory;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.repository.ProgrammeRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SchoolYearRepositoryTest {

    // Valid addition

    @Test
    void createEmptyRepositorySuccessfully() {
        //arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);

        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        //act
        new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSchoolYearFactoryIsNull() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new SchoolYearRepository(null, schoolYearListFactoryImplDouble));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSchoolYearListFactoryIsNull() {
        // Arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new SchoolYearRepository(schoolYearFactoryImplDouble, null));
    }

    @Test
    void testAddSchoolYearSuccessfully() throws Exception {
        // Arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        SchoolYear schoolYearDouble = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble);

        ArrayList<SchoolYear> schoolYearListDouble = mock(ArrayList.class);
        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(schoolYearListDouble);

        // Act
        boolean result = repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        // Assert
        assertTrue(result);
    }

    @Test
    void testAddSchoolYearsWithDifferentDatesSuccessfully() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        ArrayList<SchoolYear> schoolYearListDouble = mock(ArrayList.class);
        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(schoolYearListDouble);

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
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble2);

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(true);

        // Act & Assert
        assertThrows(Exception.class, () -> repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"));
    }

    @Test
    void shouldReturnTheCurrentSchoolYearWhenThereAreMultipleYearsAheadInTheRepository() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        when(schoolYearDouble1.getStartDate()).thenReturn(LocalDate.of(2023, 9, 1));
        when(schoolYearDouble1.getEndDate()).thenReturn(LocalDate.of(2024, 8, 31));
        when(schoolYearDouble2.getStartDate()).thenReturn(LocalDate.of(2024, 9, 1));
        when(schoolYearDouble2.getEndDate()).thenReturn(LocalDate.of(2025, 8, 31));

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, true, false);
        when(iterator.next()).thenReturn(schoolYearDouble1, schoolYearDouble2);

        // Act
        SchoolYear sy1 = repository.getCurrentSchoolYear();

        // Assert
        assertEquals(sy1, schoolYearDouble2);
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryDoesNotHaveCurrentSchoolYear() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 26/27", "01-09-2025", "31-08-2026"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear("School Year 26/27", "01-09-2025", "31-08-2026");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        when(schoolYearDouble1.getStartDate()).thenReturn(LocalDate.of(2026, 9, 1));
        when(schoolYearDouble1.getEndDate()).thenReturn(LocalDate.of(2027, 8, 31));
        when(schoolYearDouble2.getStartDate()).thenReturn(LocalDate.of(2023, 9, 1));
        when(schoolYearDouble2.getEndDate()).thenReturn(LocalDate.of(2024, 8, 31));

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);

        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(schoolYearDouble1);

        // Act
        SchoolYear currentSchoolYear = repository.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
    }

    @Test
    void shouldReturnNullIfSchoolYearRepositoryIsEmpty() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(false);

        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        SchoolYear currentSchoolYear = repository.getCurrentSchoolYear();

        // Assert
        assertNull(currentSchoolYear);
    }

    //Testing schoolYearExists method
    @Test
    void shouldReturnTrueWhenSchoolYearExists() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(true);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(schoolYearDouble1);

        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble2);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearDoesNotExist() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(false);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(true, false);
        when(iterator.next()).thenReturn(schoolYearDouble1);

        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble2);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenRepositoryIsEmpty() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble = mock(SchoolYear.class);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(false);

        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        boolean result = repository.schoolYearExists(schoolYearDouble);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearIsNull() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYear schoolYear = null;
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);
        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);
        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(false);

        // Act
        boolean result = repository.schoolYearExists(schoolYear);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnTheListOfSchoolYears() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);

        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025"))
                .thenReturn(schoolYearDouble2);

        List<SchoolYear> realList = new ArrayList<>();
        realList.add(schoolYearDouble1);
        realList.add(schoolYearDouble2);

        when(schoolYearListFactoryImplDouble.copySchoolYearArrayList(anyList())).thenReturn(new ArrayList<>(realList));

        repository.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024");
        repository.addSchoolYear("School Year 24/25", "01-09-2024", "31-08-2025");

        // Act
        List<SchoolYear> result = repository.getAllSchoolYears();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnCorrectSchoolYearList() throws Exception {
        // Arrange
        SchoolYearFactoryImpl schoolYearFactoryImpl = mock(SchoolYearFactoryImpl.class);
        SchoolYearListFactoryImpl schoolYearListFactoryImpl = mock(SchoolYearListFactoryImpl.class);
        SchoolYearRepository schoolYearRepo = new SchoolYearRepository(schoolYearFactoryImpl, schoolYearListFactoryImpl);

        List<SchoolYear> schoolYearListMock = List.of(mock(SchoolYear.class), mock(SchoolYear.class));
        when(schoolYearListFactoryImpl.copySchoolYearArrayList(any())).thenReturn(schoolYearListMock);

        // Act
        List<SchoolYear> schoolYearList = schoolYearRepo.getAllSchoolYears();

        // Assert
        assertEquals(schoolYearListMock, schoolYearList, "The returned list should match the copied list");
    }
}