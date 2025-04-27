package PAI.service.schoolYear;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.domain.SchoolYear;
import PAI.factory.ISchoolYearFactory;
import PAI.repository.ISchoolYearRepository;
import org.junit.jupiter.api.Test;


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

        // Verify that save() was never called (since the school year already exists)
        verify(schoolYearRepository, never()).save(any());  // Ensure save() is NOT called
    }
}
