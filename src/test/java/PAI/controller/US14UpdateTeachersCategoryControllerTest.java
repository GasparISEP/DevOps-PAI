/* package PAI.controller;

import PAI.VOs.Date;
import PAI.VOs.Name;
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
import java.util.Optional;
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
    void shouldReturnListOfTeachers() throws Exception {
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
    void shouldReturnListOfTeacherCategories() throws Exception {
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

    @Test
    void successfullyUpdatesTeacherCategoryInTeacherCareerProgression() throws Exception {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        String date = "12-03-2024";
        String teacherCategory = "Assistant";
        String teacherAcronym = "ABC";

        TeacherCategoryID tcIDVODouble = mock(TeacherCategoryID.class);

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.of(tcIDVODouble));

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);

        when(teacherCareerProgressionRepositoryDouble.updateTeacherCategoryInTeacherCareerProgression(
                any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(true);

        //Act
        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym);

        //Assert
        assertTrue(result);
    }

    public static Stream<Arguments> provideInvalidDates() {
        return Stream.of(
                Arguments.of((Date) null),
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("2024-12-03"),
                Arguments.of("03/12/2024"),
                Arguments.of("12 de Março de 2024"),
                Arguments.of("32-01-2024"),
                Arguments.of("12-13-2024"),
                Arguments.of("03-12-24")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidDates")
    void shouldThrowExceptionWhenDateIsInvalid (String date) {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        String teacherCategory = "Assistant";
        String teacherAcronym = "ABC";

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym));
    }

    public static Stream<Arguments> provideInvalidTeacherCategory() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of(" ")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTeacherCategory")
    void shouldThrowExceptionWhenTeacherCategoryIsInvalid (String teacherCategory) throws Exception {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        String date = "12-03-2024";
        String teacherAcronym = "ABC";

        TeacherCategoryID tcIDVODouble = mock(TeacherCategoryID.class);

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.of(tcIDVODouble));

        //Act + Assert
        assertThrows (IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym));
    }

    public static Stream<Arguments> provideInvalidTeacherAcronym() {
        return Stream.of(
                Arguments.of((String) null),
                Arguments.of(""),
                Arguments.of(" "),
                Arguments.of("AB1"),
                Arguments.of("ABc"),
                Arguments.of("AB@")
        );
    }

    @ParameterizedTest
    @MethodSource("provideInvalidTeacherAcronym")
    void shouldThrowExceptionWhenTeacherAcronymIsInvalid (String teacherAcronym) {
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        String date = "12-03-2024";
        String teacherCategory = "Assistant";

        TeacherCategoryID tcIDVODouble = mock(TeacherCategoryID.class);

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.of(tcIDVODouble));

        //Act + Assert
        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym));
    }

    @Test
    void shouldReturnFalseIfTeacherCategoryIsNotRegistered() throws Exception {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        String date = "12-03-2024";
        String teacherCategory = "Assistant";
        String teacherAcronym = "ABC";

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.empty());

        //Act
        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseIfTeacherIsNotRegistered() throws Exception {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        TeacherCategoryID tcIDVODouble = mock(TeacherCategoryID.class);

        String date = "12-03-2024";
        String teacherCategory = "Assistant";
        String teacherAcronym = "ABC";

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.of(tcIDVODouble));

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(false);

        //Act
        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym);

        //Assert
        assertFalse(result);
    }

    @Test
    void shouldReturnFalseWhenUpdatingTeacherCategoryInTeacherCareerProgressionIsUnsuccessful() throws Exception {
        //Arrange
        ITeacherRepository teacherRepositoryDouble = mock(TeacherRepository.class);
        ITeacherCategoryRepository teacherCategoryRepositoryDouble = mock(TeacherCategoryRepositoryImpl.class);
        ITeacherCareerProgressionRepository teacherCareerProgressionRepositoryDouble = mock(TeacherCareerProgressionRepository.class);

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(teacherRepositoryDouble, teacherCategoryRepositoryDouble, teacherCareerProgressionRepositoryDouble);

        TeacherCategoryID tcIDVODouble = mock(TeacherCategoryID.class);

        String date = "12-03-2024";
        String teacherCategory = "Assistant";
        String teacherAcronym = "ABC";

        when(teacherCategoryRepositoryDouble.getTeacherCategoryIDFromName(any(Name.class)))
                .thenReturn(Optional.of(tcIDVODouble));

        when(teacherRepositoryDouble.containsOfIdentity(any(TeacherID.class))).thenReturn(true);

        when(teacherCareerProgressionRepositoryDouble.updateTeacherCategoryInTeacherCareerProgression(
                any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(false);

        //Act
        boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(date, teacherCategory, teacherAcronym);

        //Assert
        assertFalse(result);
    }
}

 */