package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class US15_UpdateTeacherWorkingPercentageControllerTest {

    @Test
    void newUpdateTeacherWorkingPercentageController () {

        //Arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo = new TeacherRepository(teacherFactory);

        //Act
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
        TeacherFactory teacherFactory = new TeacherFactory();
        TeacherRepository repo = new TeacherRepository(teacherFactory);
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
        TeacherFactory teacherFactory = new TeacherFactory();
        TeacherRepository repo = new TeacherRepository(teacherFactory);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        repo.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF("987654321");

        //assert
        assertTrue(optT1.isEmpty());
    }

    public static Stream<Arguments> provideInvalidNIF() {
        return Stream.of(
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of((String) null)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidNIF")
    void throwsExceptionWhenNIFIsNullBlankOrEmpty(String NIF) throws Exception {

        //arrange
        TeacherFactory teacherFactory = new TeacherFactory();
        TeacherRepository repo = new TeacherRepository(teacherFactory);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        repo.registerTeacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF(NIF);

        //assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void successfullyUpdatesTeacherWorkingPercentage () throws Exception {

        //arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo = new TeacherRepository(teacherFactory);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        boolean result = ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacher,"15-04-2008", 50);

        //assert
        assertTrue(result);
    }

    @Test
    void throwsExceptionIfGivenWorkingPercentageIsTheSameAsLastRegisteredWorkingPercentage () throws Exception {

        //arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo = new TeacherRepository(teacherFactory);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacher,"15-04-2008", 70));

        //assert
        assertEquals("Working percentage must be different than the last working percentage!", exception.getMessage());
    }

    public static Stream<Arguments> provideInvalidDates() {
        return Stream.of(
                Arguments.of("15-04-2005", "Date must be greater than the last date registered!"),
                Arguments.of("14-04-2005", "Date must be greater than the last date registered!")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDates")
    void throwsExceptionIfGivenDateIsNotAfterLastRegisteredDate(String date, String expectedException) throws Exception {

        //arrange
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo = new TeacherRepository(teacherFactory);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repo);
        TeacherCategory tc1 = new TeacherCategory("Professor Adjunto");
        Department dpt1 = new Department("MAT", "Mathematics");
        Teacher teacher = new Teacher("ABC", "Joe Doe", "abc@isep.ipp.pt", "123456789", "B106", "Doutoramento em Engenharia Informatica, 2005, ISEP", "Rua das Flores","4444-098","Porto","Portugal", "15-04-2005", tc1, 70, dpt1);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacher,date, 50));

        //assert
        assertEquals(expectedException, exception.getMessage());
    }
}