package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.repository.SchoolYearRepository;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US07_IWantToCreateASchoolYearControllerTest {

    @Test
    void createASchoolYearControllerSuccessfullyCreated() {

        //Arrange
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);

        //Act
        new US07_IWantToCreateASchoolYearController(schoolYearRepository);
    }

    @Test
    void createASchoolYearControllerNotCreated() throws IllegalArgumentException {

        //Arrange
        SchoolYearRepository schoolYearRepository = null;

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        });

        //Assert
        assertEquals("School Year Repository must not be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenYearIsSuccessfullyAdded() throws Exception {

        //Arrange
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";
        Description description = new Description(descriptionInfo);
        Date startDate = new Date (startDateInfo);
        Date endDate = new Date (endDateInfo);

        when(schoolYearRepository.addSchoolYear(description, startDate, endDate)).thenReturn(true);

        //Act
        boolean result = US07_controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenYearCannotBeAdded() throws Exception {

        // Arrange
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearRepository.addSchoolYear(any(Description.class), any(Date.class), any(Date.class)))
                .thenThrow(new Exception("School year already exists."));

        // Act & Assert
        assertThrows(Exception.class, () -> US07_controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo));
    }

    @Test
    void shouldCreateSchoolYear_IntegrationTest() throws Exception {

        //Arrange
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearListFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act + Assert
        assertTrue(controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo));
    }
}