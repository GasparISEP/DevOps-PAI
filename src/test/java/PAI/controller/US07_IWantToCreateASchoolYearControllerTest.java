package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.factory.SchoolYearListFactoryImpl;
import PAI.persistence.mem.SchoolYearRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US07_IWantToCreateASchoolYearControllerTest {

    @Test
    void createASchoolYearControllerSuccessfullyCreated() {

        //Arrange
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = mock(SchoolYearRepositoryImpl.class);

        //Act
        new US07_IWantToCreateASchoolYearController(schoolYearRepositoryImpl);
    }

    @Test
    void createASchoolYearControllerNotCreated() throws IllegalArgumentException {

        //Arrange
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = null;

        //Act
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US07_IWantToCreateASchoolYearController(schoolYearRepositoryImpl);
        });

        //Assert
        assertEquals("School Year Repository must not be null.", exception.getMessage());
    }

    @Test
    void shouldReturnTrueWhenYearIsSuccessfullyAdded() throws Exception {

        //Arrange
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = mock(SchoolYearRepositoryImpl.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepositoryImpl);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";
        Description description = new Description(descriptionInfo);
        Date startDate = new Date (startDateInfo);
        Date endDate = new Date (endDateInfo);

        when(schoolYearRepositoryImpl.addSchoolYear(description, startDate, endDate)).thenReturn(true);

        //Act
        boolean result = US07_controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenYearCannotBeAdded() throws Exception {

        // Arrange
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = mock(SchoolYearRepositoryImpl.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepositoryImpl);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        when(schoolYearRepositoryImpl.addSchoolYear(any(Description.class), any(Date.class), any(Date.class)))
                .thenThrow(new Exception("School year already exists."));

        // Act & Assert
        assertThrows(Exception.class, () -> US07_controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo));
    }

    @Test
    void shouldCreateSchoolYear_IntegrationTest() throws Exception {

        //Arrange
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepositoryImpl schoolYearRepositoryImpl = new SchoolYearRepositoryImpl(schoolYearFactory, schoolYearListFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearRepositoryImpl);
        String descriptionInfo = "School Year 24/25";
        String startDateInfo = "24-09-2024";
        String endDateInfo = "31-06-2025";

        // Act + Assert
        assertTrue(controller.addSchoolYear(descriptionInfo, startDateInfo, endDateInfo));
    }
}