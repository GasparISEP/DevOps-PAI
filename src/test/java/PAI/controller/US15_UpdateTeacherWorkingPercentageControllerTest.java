package PAI.controller;

import PAI.domain.*;
import PAI.repository.TeacherRepository;
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
/*
    @Test
    void successfullyGetsTeacherByNIF () throws IllegalArgumentException {

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
    void returnsEmptyOptionalWhenNIFNotFoundInTeacherRepository () throws IllegalArgumentException {

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
    void throwsExceptionWhenNIFIsNullBlankOrEmpty(String NIF) throws IllegalArgumentException {

        //arrange
        TeacherRepository repositoryDouble = mock(TeacherRepository.class);
        US15_UpdateTeacherWorkingPercentageController ctrl = new US15_UpdateTeacherWorkingPercentageController(repositoryDouble);

        when(repositoryDouble.getTeacherByNIF(NIF)).thenReturn(Optional.empty());

        //act
        Optional<Teacher> optT1 = ctrl.getTeacherByNIF(NIF);

        //assert
        assertTrue(optT1.isEmpty());
    }*/
}