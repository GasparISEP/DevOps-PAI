package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Description;
import PAI.VOs.SchoolYearID;
import PAI.factory.ISchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.repository.SchoolYearRepository;
import PAI.factory.ISchoolYearListFactory;
import PAI.factory.SchoolYearListFactoryImpl;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
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
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("24-09-2021");
        Date endDate = new Date ("31-06-2022");

        when(schoolYearRepository.addSchoolYear(schoolYearID, description, startDate, endDate)).thenReturn(true);

        //Act
        boolean result = US07_controller.addSchoolYear(schoolYearID, description, startDate, endDate);

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenYearCanNotBeAdded() throws Exception {

        //Arrange
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("24-09-2021");
        Date endDate = new Date ("20-06-2022");

        when(schoolYearRepository.addSchoolYear(schoolYearID,description, startDate, endDate)).
                thenThrow(new IllegalArgumentException("School year already exists."));

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> { US07_controller.addSchoolYear(schoolYearID,description, startDate, endDate);
        });
    }

    @Test
    void shouldCreateSchoolYear_IntegrationTest() throws Exception {

        //Arrange
        ISchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        ISchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearListFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);
        SchoolYearID schoolYearID = new SchoolYearID();
        Description description = new Description("School Year 24/25");
        Date startDate = new Date ("01-09-2023");
        Date endDate = new Date ("31-08-2024");

        // Act + Assert
        assertTrue(controller.addSchoolYear(schoolYearID, description, startDate, endDate));
    }
}