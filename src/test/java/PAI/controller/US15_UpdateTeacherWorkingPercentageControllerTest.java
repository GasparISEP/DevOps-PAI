package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class US15_UpdateTeacherWorkingPercentageControllerTest {

    @Test
    void newUpdateTeacherWorkingPercentageController () {

        //arrange
        TeacherRepository repo = new TeacherRepository();

        //act
        new US15_UpdateTeacherWorkingPercentageController(repo);
    }

    @Test
    void testConstructorWithNullRepository() {
        // Arrange
        TeacherRepository repo = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US15_UpdateTeacherWorkingPercentageController(repo);
        });
        assertEquals("Teacher Repository cannot be null", exception.getMessage());
    }

    @Test
    void successfullyGetsTeacherByNIF () throws Exception {

        //arrange
        TeacherRepository repo = new TeacherRepository();
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        repo.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF("123456789");

        //assert
        assertTrue(optT1.isPresent());
    }

    @Test
    void returnsEmptyOptionalWhenNIFNotFoundInTeacherRepository () throws Exception {

        //arrange
        TeacherRepository repo = new TeacherRepository();
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        repo.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF("987654321");

        //assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void successfullyUpdatesTeacherWorkingPercentage () throws Exception {

        //arrange
        TeacherRepository repo = new TeacherRepository();
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        boolean result = ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacher,"15-04-2008", 50);

        //assert
        assertTrue(result);
    }
}