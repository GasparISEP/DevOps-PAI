package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.domain.repositoryInterfaces.schoolYear.ISchoolYearRepository;
import PAI.domain.schoolYear.ISchoolYearFactory;
import PAI.domain.schoolYear.SchoolYear;
import PAI.assembler.schoolYear.ISchoolYearAssembler;
import PAI.assembler.schoolYear.SchoolYearAssembler;
import PAI.persistence.mem.schoolYear.ISchoolYearListFactory;
import PAI.domain.schoolYear.SchoolYearFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearListFactoryImpl;
import PAI.persistence.mem.schoolYear.SchoolYearRepositoryImpl;
import PAI.service.schoolYear.ISchoolYearService;
import PAI.service.schoolYear.SchoolYearServiceImpl;
import org.junit.jupiter.api.Test;


import java.util.UUID;

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
        assertEquals("Services cannot be null.", exception.getMessage());
    }

    // Verify exception is thrown for null parameters
    @Test
    void shouldThrowExceptionWhenNullParametersArePassed() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        when(schoolYearService.addSchoolYearDM(null,null, null, null))
                .thenThrow(new Exception("Invalid input: "));


        // Act & Assert
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(null ,null, null, null));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    @Test
    void shouldThrowExceptionWhenEndDateIsNull() throws Exception{
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        Description description = mock(Description.class);
        Date startDate = mock(Date.class);
        UUID uuid = mock(UUID.class);
        when(schoolYearService.addSchoolYearDM(uuid, description, startDate, null))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));

        String description1 = "ola";
        String startDate1 = "2025-12-12";
        String id = "550e8400-e29b-41d4-a716-446655440000";

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(id, description1, startDate1, null));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }


    @Test
    void shouldThrowExceptionWhenStartDateIsNull() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        Description description = mock(Description.class);
        Date endDate = mock(Date.class);
        UUID uuid = mock(UUID.class);
        String description1 = "ola";
        String endDate1 = "22/22/22";
        String id = "550e8400-e29b-41d4-a716-446655440000";
        when(schoolYearService.addSchoolYearDM(UUID.fromString(id), description, null, endDate))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(id, description1, null, endDate1));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIsNull() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        Date startDate = mock(Date.class);
        Date endDate = mock(Date.class);
        UUID uuid = mock(UUID.class);
        String startDate1 = "21/22/22";
        String endDate1 = "22/22/22";
        String id = "550e8400-e29b-41d4-a716-446655440000";
        when(schoolYearService.addSchoolYearDM(UUID.fromString(id),null, startDate, endDate))
                .thenThrow(new IllegalArgumentException("Parameters cannot be null"));

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                controller.addSchoolYear(id,null, startDate1, endDate1));

        assertTrue(exception.getMessage().startsWith("Invalid input:"), "Exception message should indicate invalid input");
    }

    // Verify successful addition of school year
    @Test
    void shouldReturnASchoolYearWhenYearIsSuccessfullyAdded() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";
        String id = "550e8400-e29b-41d4-a716-446655440000";

        when(schoolYearService.addSchoolYearDM(any(UUID.class), any(Description.class), any(Date.class), any(Date.class)))
                .thenReturn(schoolYear);

        // Act
        SchoolYear result = controller.addSchoolYear(id, descriptionInfo, startDateInfo, endDateInfo);

        // Assert
        assertEquals(result,schoolYear);
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
        String id = "550e8400-e29b-41d4-a716-446655440000";

        // Act
        SchoolYear result = controller.addSchoolYear(id, descriptionInfo, startDateInfo, endDateInfo);

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
        String id = "550e8400-e29b-41d4-a716-446655440000";

        when(schoolYearService.addSchoolYearDM(any(UUID.class),any(Description.class), any(Date.class), any(Date.class)))
                .thenThrow(new Exception("School year already exists."));

        // Act & Assert
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(id, descriptionInfo, startDateInfo, endDateInfo)
        );

        assertTrue(exception.getMessage().contains("School year already exists."));
    }


    // Verify allowing multiple different school years creation
    @Test
    void shouldAllowMultipleDifferentSchoolYearsCreation() throws Exception {
        // Arrange
        ISchoolYearService schoolYearService = mock(ISchoolYearService.class);
        SchoolYear schoolYear = mock(SchoolYear.class);
        SchoolYear schoolYear1 = mock(SchoolYear.class);
        String id = "550e8400-e29b-41d4-a716-446655440000";
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        when(schoolYearService.addSchoolYearDM(any(UUID.class),any(Description.class), any(Date.class), any(Date.class)))
                .thenReturn(schoolYear, schoolYear1);

        // Act & Assert
        assertEquals(controller.addSchoolYear(id,"School Year 24/25", "24-09-2024", "31-06-2025"),schoolYear);
        assertEquals(controller.addSchoolYear(id,"School Year 25/26", "24-09-2025", "31-06-2026"),schoolYear1);
    }


    //---------------Integration Tests--------------

    @Test
    void integrationTest_ShouldCreateSchoolYearSuccessfully() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearRepository schoolYearRepositoryImpl = new SchoolYearRepositoryImpl(schoolYearListFactory);
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepositoryImpl, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";
        String id = "550e8400-e29b-41d4-a716-446655440000";

        // Act: Try to create a new school year
        SchoolYear result = controller.addSchoolYear(id,descriptionInfo, startDateInfo, endDateInfo);

        // Assert: Should return true
        assertNotNull(result);
    }

    @Test
    void integrationTest_ShouldThrowExceptionWhenSchoolYearAlreadyExists() throws Exception {
        // Arrange: Create real dependencies
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        SchoolYearRepositoryImpl schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactory);
        SchoolYearServiceImpl schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);

        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";
        String id = "550e8400-e29b-41d4-a716-446655440000";

        // Act: Create the school year the first time
        SchoolYear created = controller.addSchoolYear(id,descriptionInfo, startDateInfo, endDateInfo);
        assertNotNull(created, "First creation should succeed.");

        // Act & Assert: Creating the same school year again should throw an exception
        Exception exception = assertThrows(Exception.class, () ->
                controller.addSchoolYear(id, descriptionInfo, startDateInfo, endDateInfo)
        );

        // Assert: Check that the exception message matches expected message
        assertTrue(exception.getMessage().contains("already exists"), "Exception message should indicate that the school year already exists.");
    }


    @Test
    void integrationTest_ShouldAllowMultipleDifferentSchoolYearsCreation() throws Exception {
        // Arrange
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        ISchoolYearAssembler schoolYearMapperDTO = new SchoolYearAssembler(schoolYearFactory);
        ISchoolYearRepository schoolYearRepository = new SchoolYearRepositoryImpl(schoolYearListFactory);
        ISchoolYearService schoolYearService = new SchoolYearServiceImpl(schoolYearRepository, schoolYearFactory, schoolYearMapperDTO);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearService);
        String id = "550e8400-e29b-41d4-a716-446655440000";

        // Act & Assert
        assertNotNull(controller.addSchoolYear(id,"School Year 24/25", "24-09-2024", "31-06-2025"));
        assertNotNull(controller.addSchoolYear(id,"School Year 25/26", "24-09-2025", "31-06-2026"));
    }

}