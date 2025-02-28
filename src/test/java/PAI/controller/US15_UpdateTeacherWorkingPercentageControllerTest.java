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
import static org.mockito.Mockito.when;

class US15_UpdateTeacherWorkingPercentageControllerTest {

    @Test
    void newUpdateTeacherWorkingPercentageController () {

        //Arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);

        //Act
        new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);
    }

    @Test
    void testConstructorWithNullRepository() {
        // Arrange
        TeacherRepository teacherRepository = null;

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US15_UpdateTeacherWorkingPercentageController(teacherRepository);
        });
        assertEquals("Teacher Repository cannot be null", exception.getMessage());
    }

    @Test
    void successfullyGetsTeacherByNIF () throws Exception {

        //arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);
        Teacher teacherDouble = mock(Teacher.class);

        when(repositoryDouble.getTeacherByNIF("123456789")).thenReturn(Optional.of(teacherDouble));

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF("123456789");

        //assert
        assertTrue(optT1.isPresent());
    }

    @Test
    void returnsEmptyOptionalWhenNIFNotFoundInTeacherRepository () throws Exception {

        //arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);

        when(repositoryDouble.getTeacherByNIF("477123321")).thenReturn(Optional.empty());

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
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);

        when(repositoryDouble.getTeacherByNIF(NIF)).thenReturn(Optional.empty());

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF(NIF);

        //assert
        assertTrue(optT1.isEmpty());
    }

    @Test
    void successfullyUpdatesTeacherWorkingPercentage () throws Exception {

        //arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherDouble.updateWorkingPercentageInTeacherCareerProgression("15-04-2008", 50)).thenReturn(true);

        //act
        boolean result = ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacherDouble,"15-04-2008", 50);

        //assert
        assertTrue(result);
    }

    @Test
    void throwsExceptionIfGivenWorkingPercentageIsTheSameAsLastRegisteredWorkingPercentage () throws Exception {

        //arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);
        Teacher teacherDouble = mock(Teacher.class);
        when(teacherDouble.updateWorkingPercentageInTeacherCareerProgression("15-04-2008", 70))
                .thenThrow(new IllegalArgumentException("Working percentage must be different than the last working percentage!"));

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacherDouble,"15-04-2008", 70));

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
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);
        Teacher teacherDouble = mock(Teacher.class);

        when(teacherDouble.updateWorkingPercentageInTeacherCareerProgression(date, 50))
                .thenThrow(new IllegalArgumentException("Date must be greater than the last date registered!"));

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> ctrl.updateTeacherWorkingPercentageInTeacherCareerProgression(teacherDouble, date, 50));

        //assert
        assertEquals(expectedException, exception.getMessage());
    }
}