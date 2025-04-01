package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

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
        Description description = mock(Description.class);
        Date startDateDouble = mock(Date.class);
        Date endDateDouble = mock(Date.class);
        when(schoolYearFactoryImplDouble.createSchoolYear(description, startDateDouble, endDateDouble))
                .thenReturn(schoolYearDouble);

        ArrayList<SchoolYear> schoolYearListDouble = mock(ArrayList.class);
        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(schoolYearListDouble);

        // Act
        boolean result = repository.addSchoolYear(description,startDateDouble, endDateDouble);

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
        Description description1 = mock(Description.class);
        Date startDateDouble1 = mock(Date.class);
        Date endDateDouble1 = mock(Date.class);
        Description description2 = mock(Description.class);
        Date startDateDouble2 = mock(Date.class);
        Date endDateDouble2 = mock(Date.class);
        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDateDouble1, endDateDouble1))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear(description2, startDateDouble2, endDateDouble2))
                .thenReturn(schoolYearDouble2);

        ArrayList<SchoolYear> schoolYearListDouble = mock(ArrayList.class);
        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(schoolYearListDouble);

        // Act
        boolean result1 = repository.addSchoolYear(description1, startDateDouble1,endDateDouble1);
        boolean result2 = repository.addSchoolYear(description2, startDateDouble2, endDateDouble2);

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
        Description description = mock(Description.class);
        Date startDateDouble = mock(Date.class);
        Date endDateDouble = mock(Date.class);

        when(schoolYearFactoryImplDouble.createSchoolYear(description, startDateDouble, endDateDouble))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear(description, startDateDouble,endDateDouble);

        when(schoolYearFactoryImplDouble.createSchoolYear(description, startDateDouble, endDateDouble))
                .thenReturn(schoolYearDouble2);

        when(schoolYearDouble1.isSameSchoolYear(schoolYearDouble2)).thenReturn(true);

        // Act & Assert
        assertThrows(Exception.class, () -> repository.addSchoolYear(description, startDateDouble, endDateDouble));
    }

    @Test
    void shouldReturnTheCurrentSchoolYearWhenThereAreMultipleYearsAheadInTheRepository() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        SchoolYear schoolYearDouble2 = mock(SchoolYear.class);
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Date startDateDouble1 = new Date("01-09-2023");
        Date endDateDouble1 = new Date("31-08-2024");
        Date startDateDouble2 = new Date("01-09-2024");
        Date endDateDouble2 = new Date("31-08-2025");

        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDateDouble1, endDateDouble1))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear(description2, startDateDouble2,endDateDouble2))
                .thenReturn(schoolYearDouble2);

        repository.addSchoolYear(description1, startDateDouble1,endDateDouble1);
        repository.addSchoolYear(description2, startDateDouble2, endDateDouble2);

        when(schoolYearDouble1.getStartDate()).thenReturn(startDateDouble1);
        when(schoolYearDouble1.getEndDate()).thenReturn(endDateDouble1);
        when(schoolYearDouble2.getStartDate()).thenReturn(startDateDouble2);
        when(schoolYearDouble2.getEndDate()).thenReturn(endDateDouble2);

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
    void shouldReturnNullIfSchoolYearRepositoryDoesNotHaveCurrentSchoolYear() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepository repository = new SchoolYearRepository(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        List<SchoolYear> mockSchoolYearList = mock(List.class);
        when(mockSchoolYearList.isEmpty()).thenReturn(false);

        when(mockSchoolYearList.size()).thenReturn(1);
        SchoolYear mockSchoolYear = mock(SchoolYear.class);
        when(mockSchoolYearList.get(0)).thenReturn(mockSchoolYear);

        Date mockDate = mock(Date.class);
        when(mockDate.isBefore(any())).thenReturn(false);
        when(mockDate.isAfter(any())).thenReturn(true);
        when(mockSchoolYear.getStartDate()).thenReturn(mockDate);
        when(mockSchoolYear.getEndDate()).thenReturn(mockDate);

        // Act
        SchoolYear result = repository.getCurrentSchoolYear();

        // Assert
        assertNull(result);
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
        Description description = mock(Description.class);
        Date startDateDouble = mock(Date.class);
        Date endDateDouble = mock(Date.class);

        when(schoolYearFactoryImplDouble.createSchoolYear(description, startDateDouble, endDateDouble))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear(description, startDateDouble, endDateDouble);

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
        Description description = mock(Description.class);
        Date startDateDouble = mock(Date.class);
        Date endDateDouble = mock(Date.class);

        when(schoolYearFactoryImplDouble.createSchoolYear(description, startDateDouble, endDateDouble))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear(description, startDateDouble, endDateDouble);

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
        Description description1 = mock(Description.class);
        Date startDateDouble1 = mock(Date.class);
        Date endDateDouble1 = mock(Date.class);
        Description description2 = mock(Description.class);
        Date startDateDouble2 = mock(Date.class);
        Date endDateDouble2 = mock(Date.class);

        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDateDouble1, endDateDouble1))
                .thenReturn(schoolYearDouble1);
        when(schoolYearFactoryImplDouble.createSchoolYear(description2,startDateDouble2, endDateDouble2))
                .thenReturn(schoolYearDouble2);

        List<SchoolYear> realList = new ArrayList<>();
        realList.add(schoolYearDouble1);
        realList.add(schoolYearDouble2);

        when(schoolYearListFactoryImplDouble.copySchoolYearArrayList(anyList())).thenReturn(new ArrayList<>(realList));

        repository.addSchoolYear(description1, startDateDouble1, endDateDouble1);
        repository.addSchoolYear(description2, startDateDouble2, endDateDouble1);

        // Act
        List<SchoolYear> result = repository.getAllSchoolYears();

        // Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnCorrectSchoolYearList()  {
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