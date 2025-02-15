package PAI.controller;

import PAI.domain.SchoolYear;
import PAI.domain.SchoolYearRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class US07_IWantToCreateASchoolYearControllerTest {

    @Test
    void createASchoolYearControllerSuccessfullyCreated() {

        //Arrange
        SchoolYearRepository schoolYearRepository = new SchoolYearRepository();

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
    void addedSchoolYearSuccessfully() throws Exception {

        //Arrange
        SchoolYearRepository syRepo = new SchoolYearRepository();
        US07_IWantToCreateASchoolYearController ctrl = new US07_IWantToCreateASchoolYearController(syRepo);
        SchoolYear schoolYear = new SchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");

        //Act
        boolean result = ctrl.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");

        //Assert
        assertEquals(true, result);
    }

    @Test
    void schoolYearNotAddedBecauseItAlreadyExists() throws Exception {

        // Arrange
        SchoolYearRepository syRepo = new SchoolYearRepository();
        US07_IWantToCreateASchoolYearController ctrl = new US07_IWantToCreateASchoolYearController(syRepo);
        ctrl.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");

        // Act and Assert
        Exception exception = assertThrows(Exception.class, () -> {
            ctrl.addSchoolYear("Ano Letivo de:", "24-09-2021", "20-06-2022");
        });

        assertEquals("School year already exists.", exception.getMessage());
    }
}