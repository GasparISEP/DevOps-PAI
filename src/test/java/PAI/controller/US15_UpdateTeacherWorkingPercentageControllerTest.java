package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.TeacherID;
import PAI.VOs.WorkingPercentage;
import PAI.domain.Teacher;
import PAI.repository.ITeacherCareerProgressionRepository;
import PAI.repository.ITeacherRepository;
import PAI.repository.TeacherCareerProgressionRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US15_UpdateTeacherWorkingPercentageControllerTest {

    @Test
    void newUpdateTeacherWorkingPercentageController() {

        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        //Act
        new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);
    }

    @Test
    void testConstructorWithNullTeacherRepository() {
        // Arrange
        ITeacherRepository teacherRepositoryDouble = null;
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);
        });
        assertEquals("Repository cannot be null", exception.getMessage());
    }

    @Test
    void testConstructorWithNullTeacherCareerProgressionRepository() {
        // Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = null;


        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);
        });
        assertEquals("Repository cannot be null", exception.getMessage());
    }

    @Test
    void shouldReturnListOfTeachers() throws Exception {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
        List<Teacher> listDouble = mock(List.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherRepositoryDouble.findAll()).thenReturn(listDouble);

        //act
        Iterable<Teacher> result = controller.findAll();

        //assert
        assertEquals(result, listDouble);
    }

    @Test
    void successfullyUpdatesWorkingPercentageInTeacherCareerProgression() throws Exception {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);

        when(teacherCareerProgressionRepositoryDouble.updateWorkingPercentageInTeacherCareerProgression(
                any(Date.class), any(WorkingPercentage.class), any(TeacherID.class))).thenReturn(true);

        //act
        boolean result = controller.updateWorkingPercentageInTeacherCareerProgression("12-03-2024", 70, "ABC");

        //assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNotRegistered() throws Exception {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(false);

        //act
        boolean result = controller.updateWorkingPercentageInTeacherCareerProgression("12-03-2024", 70, "ABC");

        //assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUpdateWorkingPercentageInTeacherCareerProgressionUnsuccessful() throws Exception {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);

        when(teacherCareerProgressionRepositoryDouble.updateWorkingPercentageInTeacherCareerProgression(
                any(Date.class), any(WorkingPercentage.class), any(TeacherID.class))).thenReturn(false);

        //act
        boolean result = controller.updateWorkingPercentageInTeacherCareerProgression("12-03-2024", 70, "ABC");

        //assert
        assertFalse(result);
    }

    public static Stream<Arguments> provideInvalidWorkingPercentage() {
        return Stream.of(
                Arguments.of(-1),
                Arguments.of(101)
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidWorkingPercentage")
    void shouldThrowExceptionWhenWorkingPercentageIsInvalid (int workingPercentage) {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> controller.updateWorkingPercentageInTeacherCareerProgression("12-03-2024", workingPercentage, "ABC"));

        //assert
        assertEquals(exception.getMessage(), "Working Percentage must be a value between 0 and 100.");
    }

    public static Stream<Arguments> provideInvalidDates() {
        return Stream.of(
                Arguments.of(null, "Date cannot be empty!"),
                Arguments.of("", "Date cannot be empty!"),
                Arguments.of(" ", "Date cannot be empty!"),
                Arguments.of("2024-12-03", "Invalid date format. Please use dd-MM-yyyy."),
                Arguments.of("03/12/2024", "Invalid date format. Please use dd-MM-yyyy."),
                Arguments.of("12 de Março de 2024", "Invalid date format. Please use dd-MM-yyyy."),
                Arguments.of("32-01-2024", "Invalid date format. Please use dd-MM-yyyy."),
                Arguments.of("12-13-2024", "Invalid date format. Please use dd-MM-yyyy."),
                Arguments.of("03-12-24", "Invalid date format. Please use dd-MM-yyyy.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDates")
    void shouldThrowExceptionWhenDateIsInvalid (String date, String exceptionThrown) {
        //arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        //act
        Exception exception = assertThrows(IllegalArgumentException.class, () -> controller.updateWorkingPercentageInTeacherCareerProgression(date, 70, "ABC"));

        //assert
        assertEquals(exception.getMessage(), exceptionThrown);
    }

    public static Stream<Arguments> provideInvalidTeacherAcronym() {
        return Stream.of(
                Arguments.of(null, "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of("", "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of(" ", "Acronym must be a 3 capital letter non-empty String."),
                Arguments.of("AB1", "Acronym must contain only three capital letters."),
                Arguments.of("ABc", "Acronym must contain only three capital letters."),
                Arguments.of("AB@", "Acronym must contain only three capital letters.")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTeacherAcronym")
    void shouldThrowExceptionWhenTeacherAcronymIsInvalid (String teacherAcronym, String exceptionThrown) {
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US15_UpdateTeacherWorkingPercentageController controller = new US15_UpdateTeacherWorkingPercentageController(teacherRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        //act
        Exception exception = assertThrows(Exception.class, () -> controller.updateWorkingPercentageInTeacherCareerProgression("12-03-2024", 70, teacherAcronym));

        //assert
        assertEquals(exception.getMessage(), exceptionThrown);
    }
}