package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.TeacherCategoryID;
import PAI.VOs.TeacherID;
import PAI.domain.Teacher;
import PAI.domain.TeacherCategory;
import PAI.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class US14UpdateTeachersCategoryControllerTest {

    @Test
    void shouldCreateUpdateTeacherCategoryController() {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        //Act
        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        //Assert
        assertNotNull(controller);
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherRepository() {
        // Arrange
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
            new US14_UpdateTeachersCategoryController(null, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble));
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherCategoryRepository() {
        // Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, null, teacherCareerProgressionRepositoryDouble));
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherCareerProgressionRepository() {
        // Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () ->
                new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, null));
    }

    @Test
    void shouldReturnListOfTeachers() {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
        List<Teacher> listDouble = mock(List.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherRepositoryDouble.findAll()).thenReturn(listDouble);

        //Act
        Iterable<Teacher> result = controller.findAllTeachers();

        //Assert
        assertEquals(result, listDouble);
    }

    @Test
    void shouldReturnListOfTeacherCategories() {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
        List<TeacherCategory> listDouble = mock(List.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        when(teacherCategoryRepositoryDouble.findAll()).thenReturn(listDouble);

        //Act
        Iterable<TeacherCategory> result = controller.findAllTeacherCategories();

        //Assert
        assertEquals(result, listDouble);
    }

//    @Test
//    void successfullyUpdatesTeacherCategoryInTeacherCareerProgression() throws Exception {
//        //Arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        Name nameTCVO = mock(Name.class);
//        TeacherCategoryID tcIDVO = mock(TeacherCategoryID.class);
//
//        String date = "12-03-2024";
//        String teacherCategory = "Assistant";
//        String teacherAcronym = "ABC";
//
//
//        when(getTeacherCategoryIDFromName(nameTeacherCategoryVO)).thenReturn(tcIDVO);
//
//        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);
//
//        when(teacherCareerProgressionRepositoryDouble.updateTeacherCategoryInTeacherCareerProgression(
//                any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(true);
//
//        //Act
//        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym);
//
//        //Assert
//        assertTrue(result);
//    }
//
//    public static Stream<Arguments> provideInvalidDates() {
//        return Stream.of(
//                Arguments.of(null, "Date cannot be empty!"),
//                Arguments.of("", "Date cannot be empty!"),
//                Arguments.of(" ", "Date cannot be empty!"),
//                Arguments.of("2024-12-03", "Invalid date format. Please use dd-MM-yyyy."),
//                Arguments.of("03/12/2024", "Invalid date format. Please use dd-MM-yyyy."),
//                Arguments.of("12 de Março de 2024", "Invalid date format. Please use dd-MM-yyyy."),
//                Arguments.of("32-01-2024", "Invalid date format. Please use dd-MM-yyyy."),
//                Arguments.of("12-13-2024", "Invalid date format. Please use dd-MM-yyyy."),
//                Arguments.of("03-12-24", "Invalid date format. Please use dd-MM-yyyy.")
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideInvalidDates")
//    void shouldThrowExceptionWhenDateIsInvalid (String date, String exceptionThrown) {
//        //arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        //Act + Assert
//        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression(date, "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC"));
//    }
//
//    public static Stream<Arguments> provideInvalidWorkingPercentage() {
//        return Stream.of(
//                Arguments.of(-1),
//                Arguments.of(101)
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideInvalidWorkingPercentage")
//    void shouldThrowExceptionWhenWorkingPercentageIsInvalid (int workingPercentage) {
//        //Arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);
//
//        when(teacherCategoryRepositoryDouble.containsOfIdentity(any(TeacherCategoryID.class))).thenReturn(true);
//
//        //Act + Assert
//        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression("12-03-2024", "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC"));
//    }
//
//
//
//    public static Stream<Arguments> provideInvalidTeacherAcronym() {
//        return Stream.of(
//                Arguments.of(null, "Acronym must be a 3 capital letter non-empty String."),
//                Arguments.of("", "Acronym must be a 3 capital letter non-empty String."),
//                Arguments.of(" ", "Acronym must be a 3 capital letter non-empty String."),
//                Arguments.of("AB1", "Acronym must contain only three capital letters."),
//                Arguments.of("ABc", "Acronym must contain only three capital letters."),
//                Arguments.of("AB@", "Acronym must contain only three capital letters.")
//        );
//    }
//
//    @ParameterizedTest
//    @MethodSource("provideInvalidTeacherAcronym")
//    void shouldThrowExceptionWhenTeacherAcronymIsInvalid (String teacherAcronym, String exceptionThrown) {
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        //Act + Assert
//        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression("12-03-2024", "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC"));
//    }
//
//    @Test
//    void shouldReturnFalseIfTeacherIsNotRegistered() throws Exception {
//        //Arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(false);
//
//        //Act
//        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression("12-03-2024", "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC");
//
//        //Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseIfTeacherCategoryIsNotRegistered() throws Exception {
//        //Arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);
//
//        when(teacherCategoryRepositoryDouble.containsOfIdentity(any(TeacherCategoryID.class))).thenReturn(false);
//
//        //Act
//        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression("12-03-2024", "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC");
//
//        //Assert
//        assertFalse(result);
//    }
//
//    @Test
//    void shouldReturnFalseWhenUpdatingTeacherCategoryInTeacherCareerProgressionIsUnsuccessful() throws Exception {
//        //Arrange
//        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
//        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
//        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);
//
//        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);
//
//        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);
//
//        when(teacherCategoryRepositoryDouble.containsOfIdentity(any(TeacherCategoryID.class))).thenReturn(true);
//
//        when(teacherCareerProgressionRepositoryDouble.updateTeacherCategoryInTeacherCareerProgression(
//                any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(false);
//
//        //Act
//        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression("12-03-2024", "d290f1ee-6c54-4b01-90e6-d701748f0851", "ABC");
//
//        //Assert
//        assertFalse(result);
//    }
}