package PAI.repository;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

class SchoolYearRepositoryImplTest {

    // Valid addition

    @Test
    void createEmptyRepositorySuccessfully() {
        //arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);

        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        //act
        new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
    }
    @Test
    void shouldThrowIllegalArgumentExceptionWhenSchoolYearFactoryIsNull() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new SchoolYearRepositoryImpl(null, schoolYearListFactoryImplDouble));
    }

    @Test
    void shouldThrowIllegalArgumentExceptionWhenSchoolYearListFactoryIsNull() {
        // Arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, null));
    }

    @Test
    void testAddSchoolYearSuccessfully() throws Exception {
        // Arrange
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
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
        Optional<SchoolYear> sy1 = repository.getCurrentSchoolYear();

        // Assert
        assertEquals(sy1, Optional.of(schoolYearDouble2));
    }

    @Test
    void shouldReturnOptionalEmptyIfSchoolYearRepositoryDoesNotHaveCurrentSchoolYear() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
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
        Optional<SchoolYear> result = repository.getCurrentSchoolYear();

        // Assert
        assertEquals(Optional.empty(),result);
    }

    @Test
    void shouldReturnOptionalEmptyIfSchoolYearRepositoryIsEmpty() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

        ArrayList<SchoolYear> listDouble = mock(ArrayList.class);
        Iterator<SchoolYear> iterator = mock(Iterator.class);

        when(listDouble.iterator()).thenReturn(iterator);
        when(iterator.hasNext()).thenReturn(false);

        when(schoolYearListFactoryImplDouble.newArrayList()).thenReturn(listDouble);

        // Act
        Optional<SchoolYear> currentSchoolYear = repository.getCurrentSchoolYear();

        // Assert
        assertEquals(Optional.empty(),currentSchoolYear);
    }

    //Testing schoolYearExists method
    @Test
    void shouldReturnTrueWhenSchoolYearExists() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);

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
        SchoolYearRepositoryImpl schoolYearRepo = new SchoolYearRepositoryImpl(schoolYearFactoryImpl, schoolYearListFactoryImpl);

        List<SchoolYear> schoolYearListMock = List.of(mock(SchoolYear.class), mock(SchoolYear.class));
        when(schoolYearListFactoryImpl.copySchoolYearArrayList(any())).thenReturn(schoolYearListMock);

        // Act
        List<SchoolYear> schoolYearList = schoolYearRepo.getAllSchoolYears();

        // Assert
        assertEquals(schoolYearListMock, schoolYearList, "The returned list should match the copied list");
    }

    @Test
    void shouldSaveSchoolYear() throws Exception {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory= mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl list = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);

        Description description2 = mock(Description.class);
        Date startDate2 = mock(Date.class);
        Date endDate2 = mock(Date.class);

        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        when(ISchoolYearFactory.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYear1);

        when(ISchoolYearFactory.createSchoolYear(description2, startDate2, endDate2))
                .thenReturn(schoolYear2);

        // Act
        SchoolYear result1 = list.save(schoolYear1);

        // Assert
        assertEquals(schoolYear1 , result1);

    }

    @Test
    void shouldReturnAllSchoolYears() throws Exception {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory= mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);

        SchoolYear schoolYear1 = mock(SchoolYear.class);
        when(ISchoolYearFactory.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYear1);

        // Act
        repository.save(schoolYear1);
        Iterable<SchoolYear> result = repository.findAll();

        // Assert
        assertNotNull(result);
        assertTrue(result.iterator().hasNext());
        assertEquals(schoolYear1, result.iterator().next());
    }

    @Test
    void shouldThrowExceptionWhenListIsEmpty() {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory= mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        // Act & Assert
        assertThrows(IllegalStateException.class, repository::findAll);
    }

    @Test
    void shouldReturnSchoolYearWhenIdExists() {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory = mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);

        when(ISchoolYearFactory.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYear1);

        when(schoolYear1.identity()).thenReturn(schoolYearID);

        repository.save(schoolYear1);

        // Act
        Optional<SchoolYear> result = repository.ofIdentity(schoolYearID);

        // Assert
        assertTrue(result.isPresent(), "SchoolYear should be found when ID exists");
        assertEquals(schoolYear1, result.get(), "The SchoolYear returned should be the one that was saved");
    }

    @Test
    void shouldReturnTrueWhenIdExists() {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory = mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);

        when(ISchoolYearFactory.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYear1);

        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        when(schoolYear1.identity()).thenReturn(schoolYearID);

        repository.save(schoolYear1);

        // Act
        boolean result = repository.containsOfIdentity(schoolYearID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenIdDoesNotExist() {
        // Arrange
        ISchoolYearFactory ISchoolYearFactory = mock(ISchoolYearFactory.class);
        ISchoolYearListFactory ISchoolYearListFactory = mock(ISchoolYearListFactory.class);

        List<SchoolYear> mockSchoolYearList = spy(new ArrayList<>());
        when(ISchoolYearListFactory.newArrayList()).thenReturn(mockSchoolYearList);

        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(ISchoolYearFactory, ISchoolYearListFactory);

        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYearID otherID = mock(SchoolYearID.class);
        when(schoolYear1.identity()).thenReturn(schoolYearID);

        repository.save(schoolYear1);

        // Act
        boolean result = repository.containsOfIdentity(otherID);

        // Assert
        assertFalse(result);
    }

    //Testing schoolYearExistsByID method
    @Test
    void shouldReturnTrueWhenSchoolYearExistsByID() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);


        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYearDouble1);

        repository.addSchoolYear(description1, startDate1, endDate1);

        when(schoolYearDouble1.identity()).thenReturn(schoolYearID);

        // Act
        boolean result = repository.schoolYearExistsByID(schoolYearID);

        // Assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearDoesNotExistByID() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);


        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYearDouble1);

        when(schoolYearDouble1.identity()).thenReturn(schoolYearID);
        // Act
        boolean result = repository.schoolYearExistsByID(schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfSchoolYearRepoIsEmpty() throws Exception {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYearID schoolYearID = mock(SchoolYearID.class);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);

        when(schoolYearDouble1.identity()).thenReturn(schoolYearID);
        // Act
        boolean result = repository.schoolYearExistsByID(schoolYearID);

        // Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenSchoolYearIDIsNull() {
        // Arrange
        SchoolYearListFactoryImpl schoolYearListFactoryImplDouble = mock(SchoolYearListFactoryImpl.class);
        SchoolYearFactoryImpl schoolYearFactoryImplDouble = mock(SchoolYearFactoryImpl.class);
        SchoolYearRepositoryImpl repository = new SchoolYearRepositoryImpl(schoolYearFactoryImplDouble, schoolYearListFactoryImplDouble);
        SchoolYear schoolYearDouble1 = mock(SchoolYear.class);
        Description description1 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date endDate1 = mock(Date.class);


        when(schoolYearFactoryImplDouble.createSchoolYear(description1, startDate1, endDate1))
                .thenReturn(schoolYearDouble1);

        // Act
        boolean result = repository.schoolYearExistsByID(null);

        // Assert
        assertFalse(result);
    }
}