package PAI.controller;

import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.dto.schoolYear.ISchoolYearMapperDTO;
import PAI.dto.schoolYear.SchoolYearDTO;
import PAI.dto.schoolYear.SchoolYearMapperDTO;
import PAI.persistence.mem.schoolYear.ISchoolYearListFactory;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    // Verify exception is thrown for null parameters
    @Test
    void shouldThrowExceptionWhenNullParametersArePassed() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        when(schoolYearService.addSchoolYear(null, null, null))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(null, null, null));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsNull() throws Exception{
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        String description = "ola";
        String startDate = "22/22/22";
        when(schoolYearService.addSchoolYear(description, startDate, null))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(description, startDate, null));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }


    @Test
    void shouldThrowExceptionWhenStartDateIsNull() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        String description = "ola";
        String endDate = "22/22/22";
        when(schoolYearService.addSchoolYear(description, null, endDate))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(description, null, endDate));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        String startDate = "21/22/22";
        String endDate = "22/22/22";
        when(schoolYearService.addSchoolYear(null, startDate, endDate))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(null, startDate, endDate));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    // Verify successful addition of school year
    @Test
    void shouldReturnASchoolYearWhenYearIsSuccessfullyAdded() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYearDTO schoolYearDTO = mock(SchoolYearDTO.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearService.addSchoolYear(descriptionInfo,startDateInfo,endDateInfo))
                .thenReturn(schoolYearDTO);

        // Act
        SchoolYearDTO result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertEquals(result,schoolYearDTO);
    }

    // Verify failure when school year cannot be added
    @Test
    void shouldNotReturnASchoolYearWhenYearIsNotSuccessfullyAdded() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act
        SchoolYearDTO result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertNull(result);
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

        when(schoolYearService.addSchoolYear(descriptionInfo,startDateInfo,endDateInfo))
                .thenThrow(new Exception("School year already exists."));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo)
        );

        assertTrue(exception.getMessage().contains("School year already exists."));
    }


    // Verify allowing multiple different school years creation
    @Test
    void shouldAllowMultipleDifferentSchoolYearsCreation() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        SchoolYearDTO schoolYearDTO = mock(SchoolYearDTO.class);
        SchoolYearDTO schoolYearDTO1 = mock(SchoolYearDTO.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        when(schoolYearService.addSchoolYear("School Year 24/25", "24-09-2024", "31-06-2025"))
                .thenReturn(schoolYearDTO);

        when(schoolYearService.addSchoolYear("School Year 25/26", "24-09-2025", "31-06-2026"))
                .thenReturn(schoolYearDTO1);

        // Act & Assert
        assertEquals(controller.addSchoolYear("School Year 24/25", "24-09-2024", "31-06-2025"),schoolYearDTO);
        assertEquals(controller.addSchoolYear("School Year 25/26", "24-09-2025", "31-06-2026"),schoolYearDTO1);
    }


    //---------------Integration Tests--------------

    @Test
    void integrationTest_ShouldCreateSchoolYearSuccessfully() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepositoryImpl = new SchoolYearRepositoryImpl(schoolYearListFactory);
        ISchoolYearMapperDTO schoolYearMapperDTO = new SchoolYearMapperDTO(schoolYearFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepositoryImpl, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act: Try to create a new school year
        SchoolYearDTO result = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        // Assert: Should return true
        assertNotNull(result);
    }

    @Test
    void integrationTest_ShouldThrowExceptionWhenSchoolYearAlreadyExists() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearMapperDTO schoolYearMapperDTO = new SchoolYearMapperDTO(schoolYearFactory);
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act: Create the school year the first time
        SchoolYearDTO created = controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);
        assertNotNull(created, "First creation should succeed.");

        // Act & Assert: Creating the same school year again should throw an exception
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo)
        );

        // Assert: Check that the exception message matches expected message
        assertTrue(exception.getMessage().contains("already exists"), "Exception message should indicate that the school year already exists.");
    }


    @Test
    void integrationTest_ShouldAllowMultipleDifferentSchoolYearsCreation() throws Exception {
        // Arrange
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearMapperDTO schoolYearMapperDTO = new SchoolYearMapperDTO(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        // Act & Assert
        assertNotNull(controller.addSchoolYear("School Year 24/25", "24-09-2024", "31-06-2025"));
        assertNotNull(controller.addSchoolYear("School Year 25/26", "24-09-2025", "31-06-2026"));
    }

}