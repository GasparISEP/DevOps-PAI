package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.repository.ISchoolYearRepository;
import org.junit.jupiter.api.Test;


import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SchoolYearServiceImplTest {

    // Test case for constructor throwing exception when repository is null
    @Test
    void constructorShouldThrowExceptionWhenRepositoryIsNull() {
        // Arrange
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(null, schoolYearFactory);
        });

        assertEquals("schoolYearRepository cannot be null", exception.getMessage());
    }

    // Test case for constructor throwing exception when factory is null
    @Test
    void constructorShouldThrowExceptionWhenFactoryIsNull() {
        // Arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(schoolYearRepository, null);
        });

        assertEquals("schoolYearFactory cannot be null", exception.getMessage());
    }

    // Test constructor throws exception when both repository and factory are null
    @Test
    void constructorThrowsExceptionWhenRepositoryAndFactoryAreNull() {
        // Arrange
        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new SchoolYearServiceImpl(null, null);
        });

        assertEquals("schoolYearRepository cannot be null", exception.getMessage());
    }

    // Test case for adding a new school year when it does not already exist
    @Test
    void addSchoolYearShouldSaveSuccessfullyWhenSchoolYearDoesNotExist() throws Exception {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear newSchoolYear = mock(SchoolYear.class);

        // Mocks behaviour
        // Mock the factory to return a new school year
        when(schoolYearFactory.createSchoolYear(description, startDate, endDate)).thenReturn(newSchoolYear);

        when(schoolYearRepository.schoolYearExists(any())).thenReturn(false);

        // Act
        boolean result = service.addSchoolYear(description, startDate, endDate);

        // Assert
        assertTrue(result); // Verify that the result is true, meaning the school year was added successfully
    }

    // Test case for adding a new school year when it already exists in the repository
    @Test
    void addSchoolYearShouldThrowExceptionWhenSchoolYearAlreadyExists() throws Exception {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        SchoolYear existingSchoolYear = mock(SchoolYear.class);

        // Mocks behaviour
        // Mock the factory to return an existing school year
        when(schoolYearFactory.createSchoolYear(description, startDate, endDate)).thenReturn(existingSchoolYear);

        // Mock the repository to simulate that the school year already exists
        when(schoolYearRepository.schoolYearExists(any())).thenReturn(true);

        // Act & Assert: Verify the exception is thrown
        // We expect an exception to be thrown because the school year already exists in the repository
        Exception exception = assertThrows(Exception.class, () -> {
            service.addSchoolYear(description, startDate, endDate);
        });

        assertEquals("School year already exists.", exception.getMessage()); // Verify the exception message
    }

    // Test case for adding multiple different school years successfully
    @Test
    void addMultipleSchoolYearsSuccessfully() throws Exception {
        // Arrange: Mocks and setup
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);

        // Mock the description, startDate, and endDate for the school years
        Description description1 = mock(Description.class);
        Description description2 = mock(Description.class);
        Date startDate1 = mock(Date.class);
        Date startDate2 = mock(Date.class);
        Date endDate1 = mock(Date.class);
        Date endDate2 = mock(Date.class);

        // Mock the school year objects
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        SchoolYear schoolYear2 = mock(SchoolYear.class);

        // Mocks behaviour
        // Mock the factory to return new school years
        when(schoolYearFactory.createSchoolYear(description1, startDate1, endDate1)).thenReturn(schoolYear1);
        when(schoolYearFactory.createSchoolYear(description2, startDate2, endDate2)).thenReturn(schoolYear2);

        // Mock the repository to return false for school year existence check (meaning the school year doesn't exist yet)
        when(schoolYearRepository.schoolYearExists(any())).thenReturn(false);

        // Act: Try to add two different school years
        boolean result1 = service.addSchoolYear(description1, startDate1, endDate1);
        boolean result2 = service.addSchoolYear(description2, startDate2, endDate2);

        // Assert: Verify that both school years are added successfully
        assertTrue(result1);
        assertTrue(result2);
    }

    @Test
    void shouldReturnOptionalSchoolYearIdWhenSchoolYearExists() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory);
        SchoolYear schoolYear1 = mock(SchoolYear.class);

        SchoolYearID schoolYearID1 = mock(SchoolYearID.class);
        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.of(schoolYear1));
        when(schoolYear1.identity()).thenReturn(schoolYearID1);
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isPresent());
        assertEquals(schoolYearID1,result.get());
    }

    @Test
    void shouldReturnOptionalEmptyWhenSchoolYearDoesNotExist() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory);

        when(schoolYearRepository.getCurrentSchoolYear()).thenReturn(Optional.empty());
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isEmpty());
    }

    @Test
    void shouldReturnOptionalEmptyWhenRepositoryThrowsException() {
        //arrange
        ISchoolYearRepository schoolYearRepository = mock(ISchoolYearRepository.class);
        ISchoolYearFactory schoolYearFactory = mock(ISchoolYearFactory.class);
        SchoolYearServiceImpl service = new SchoolYearServiceImpl(schoolYearRepository,schoolYearFactory);

        when(schoolYearRepository.getCurrentSchoolYear()).thenThrow(new NullPointerException());
        //act
        Optional<SchoolYearID> result = service.getCurrentSchoolYearID();
        //assert
        assertTrue(result.isEmpty());
    }
}
