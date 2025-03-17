package PAI.controller;

import PAI.factory.SchoolYearFactory;
import PAI.factory.SchoolYearFactoryImpl;
import PAI.repository.SchoolYearRepository;
import PAI.factory.SchoolYearListFactory;
import PAI.factory.SchoolYearListFactoryImpl;
import org.junit.jupiter.api.Test;

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

        when(schoolYearRepository.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022")).thenReturn(true);

        //Act
        boolean result = US07_controller.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");

        //Assert
        assertTrue(result);
    }

    @Test
    void shouldThrowExceptionWhenYearCanNotBeAdded() throws Exception {

        //Arrange
        SchoolYearRepository schoolYearRepository = mock(SchoolYearRepository.class);
        US07_IWantToCreateASchoolYearController US07_controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);

        when(schoolYearRepository.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022")).
                thenThrow(new IllegalArgumentException("School year already exists."));

        //Act & Assert
        assertThrows(IllegalArgumentException.class, () -> { US07_controller.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");
        });
    }

    @Test
    void shouldCreateSchoolYear_IntegrationTest() throws Exception {

        //Arrange
        SchoolYearFactory schoolYearFactory = new SchoolYearFactoryImpl();
        SchoolYearListFactory schoolYearListFactory = new SchoolYearListFactoryImpl();
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository(schoolYearFactory, schoolYearListFactory);
        US07_IWantToCreateASchoolYearController controller = new US07_IWantToCreateASchoolYearController(schoolYearRepository);

        // Act + Assert
        assertTrue(controller.addSchoolYear("School Year 23/24", "01-09-2023", "31-08-2024"));
    }
}