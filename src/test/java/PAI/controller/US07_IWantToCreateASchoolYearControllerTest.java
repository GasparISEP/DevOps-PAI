package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US07_IWantToCreateASchoolYearControllerTest {


    //---------------Isolated Unit Tests--------------

    // Verify controller creation with a valid service
    @Test
    void shouldCreateControllerSuccessfullyWhenServiceIsProvided() {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);

        // Act
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        // Assert
        assertNotNull(controller);
    }

    // Verify exception is thrown when service is null
    @Test
    void shouldThrowExceptionWhenServiceIsNull() throws IllegalArgumentException {
        // Arrange
        ISchoolYearService schoolYearService = null;

        // Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US07_IWantToCreateASchoolYearController(schoolYearService);
        });

        // Assert
        assertEquals("School Year Service must not be null.", exception.getMessage());
    }

    // Verify exception is thrown for null parameters
    @Test
    void shouldThrowExceptionWhenNullParametersArePassed() {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> controller.addSchoolYear(null,null,null));
    }

    // Verify exception for each null parameter (description, start date, end date)
    @Test
    void shouldThrowExceptionWhenAnyParameterIsNull() {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        // Act & Assert
        IllegalArgumentException exceptionForDescription = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(null, "24-09-2024", "31-06-2025"));
        assertEquals("Description cannot be null or empty", exceptionForDescription.getMessage());

        IllegalArgumentException exceptionForStartDate = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear("School Year 24/25", null, "31-06-2025"));
        assertEquals("Date cannot be empty!", exceptionForStartDate.getMessage());

        IllegalArgumentException exceptionForEndDate = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear("School Year 24/25", "24-09-2024", null));
        assertEquals("Date cannot be empty!", exceptionForEndDate.getMessage());
    }

    // Verify successful addition of school year
    @Test
    void shouldReturnTrueWhenYearIsSuccessfullyAdded() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearService.addSchoolYear(any(Description.class), any(Date.class), any(Date.class)))
                .thenReturn(true);

        // Act
        boolean result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertTrue(result);
    }

    // Verify failure when school year cannot be added
    @Test
    void shouldReturnFalseWhenYearIsNotSuccessfullyAdded() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearService.addSchoolYear(any(Description.class), any(Date.class), any(Date.class)))
                .thenReturn(false);

        // Act
        boolean result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertFalse(result);
    }

    // Verify exception when school year already exists
    @Test
    void shouldThrowExceptionWhenSchoolYearAlreadyExists() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearService.addSchoolYear(any(Description.class), any(Date.class), any(Date.class)))
                .thenThrow(new Exception("School year already exists."));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () -> controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo));
        assertEquals("School year already exists.", exception.getMessage());
    }


    //---------------Integration Tests--------------
    @Test
    void integrationTest_ShouldCreateSchoolYearSuccessfully() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearListFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepositoryImpl, schoolYearFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act: Try to create a new school year
        boolean result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert: Should return true
        assertTrue(result);
    }

    @Test
    void integrationTest_ShouldThrowExceptionWhenSchoolYearAlreadyExists() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearListFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act: Create the school year the first time
        boolean created = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        assertTrue(created, "First creation should succeed.");

        // Act & Assert: Creating the same school year again should throw an exception
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo)
        );

        // Assert: Check that the exception message matches
        assertEquals("School year already exists.", exception.getMessage());
    }

}