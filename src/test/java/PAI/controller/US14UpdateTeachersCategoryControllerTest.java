 package PAI.controller;

import PAI.VOs.*;
import PAI.domain.Teacher;
import PAI.domain.TeacherCategory;
import PAI.service.ITeacherCareerProgressionService;
import PAI.service.ITeacherCategoryService;
import PAI.service.ITeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedConstruction;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

 class US14UpdateTeachersCategoryControllerTest {

    private ITeacherService _teacherServiceDouble;
    private ITeacherCategoryService _teacherCategoryServiceDouble;
    private ITeacherCareerProgressionService _teacherCareerProgressionServiceDouble;

    @BeforeEach
    //Arrange
    void setUp(){
        _teacherServiceDouble = mock(ITeacherService.class);
        _teacherCategoryServiceDouble = mock(ITeacherCategoryService.class);
        _teacherCareerProgressionServiceDouble = mock(ITeacherCareerProgressionService.class);
    }

    @Test
    void shouldCreateUpdateTeacherCategoryController() {
        //Arrange

        //Act
        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(_teacherServiceDouble,
                _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);
        //Assert
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherService() {
        // Arrange

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
            new US14_UpdateTeachersCategoryController(null, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherCategoryService() {
        // Arrange

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new US14_UpdateTeachersCategoryController(_teacherServiceDouble, null, _teacherCareerProgressionServiceDouble));
    }

    @Test
    void shouldNotCreateUpdateTeacherCategoryControllerIfNullTeacherCareerProgressionService() {
        // Arrange

        // Act + Assert
        assertThrows(NullPointerException.class, () ->
                new US14_UpdateTeachersCategoryController(_teacherServiceDouble, _teacherCategoryServiceDouble, null));
    }

    @Test
    void shouldReturnListOfTeachers() throws Exception {
        //Arrange
        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

        List<Teacher> listDouble = List.of(mock(Teacher.class), mock(Teacher.class));
        when(_teacherServiceDouble.getAllTeachers()).thenReturn(listDouble);

        //Act
        Iterable<Teacher> result = controller.getAllTeachers();

        //Assert
        assertSame(result, listDouble);
    }

    @Test
    void shouldReturnListOfTeacherCategories() throws Exception {
        //Arrange
        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

        List<TeacherCategory> listDouble = List.of(mock(TeacherCategory.class), mock(TeacherCategory.class));
        when(_teacherCategoryServiceDouble.getAllTeacherCategories()).thenReturn(listDouble);

        //Act
        Iterable<TeacherCategory> result = controller.getAllTeacherCategories();

        //Assert
        assertEquals(result, listDouble);
    }

    @Test
    void shouldReturnTrueWhenTeacherCategoryIsSuccessfullyUpdated() throws Exception {
        //Arrange
        try (MockedConstruction<TeacherAcronym> acronymConstructorDouble = mockConstruction(TeacherAcronym.class);
            MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class);
            MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
            MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class)
        ) {

            US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                    _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

            when(_teacherServiceDouble.existsById(any(TeacherID.class))).thenReturn(true);
            when(_teacherCategoryServiceDouble.existsById(any(TeacherCategoryID.class))).thenReturn(true);
            when(_teacherCareerProgressionServiceDouble.updateTeacherCategoryInTeacherCareerProgression(
                    any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(true);

            String date = "12-03-2024";
            UUID teacherCategory = UUID.randomUUID();
            String teacherAcronym = "ABC";

            //Act
            boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                                                        date, teacherCategory, teacherAcronym);

            //Assert
            assertTrue(result);
        }
    }

     static Stream<Arguments> constructorsThatCanThrowException() {
         return Stream.of(
                 Arguments.of(Date.class),
                 Arguments.of(TeacherCategoryID.class),
                 Arguments.of(TeacherAcronym.class),
                 Arguments.of(TeacherID.class)
         );
     }
     @ParameterizedTest
     @MethodSource("constructorsThatCanThrowException")
     void shouldReturnFalseWhenCallingUpdateTeacherCategoryWithInvalidParameters(Class<?> specificClass) {
         // Arrange
         String date = "12-03-2024";
         UUID teacherCategory = UUID.fromString("11111111-1111-1111-1111-111111111111");
         String teacherAcronym = "ABC";

         try (MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class, (mock, context) -> {
                 if (specificClass.equals(Date.class)){
                     throw new IllegalArgumentException();
                 }
             });
             MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class, (mock, context) -> {
                 if (specificClass.equals(TeacherCategoryID.class)){
                     throw new IllegalArgumentException();
                 }
             });
             MockedConstruction<TeacherAcronym> teacherAcronymConstructorDouble = mockConstruction(TeacherAcronym.class, (mock, context) -> {
                 if (specificClass.equals(TeacherAcronym.class)){
                     throw new IllegalArgumentException();
                 }
             });
             MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class, (mock, context) -> {
                 if (specificClass.equals(TeacherID.class)){
                     throw new IllegalArgumentException();
                 }
             })
         ) {
             US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                     _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

             // Act
             boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                     date, teacherCategory, teacherAcronym);

             // Assert
             assertFalse(result);
         }
     }

     @Test
     void shouldReturnFalseIfTeacherDoesNotExistWhenCallingUpdateTeacherCategory() {
         //Arrange
         try (MockedConstruction<TeacherAcronym> acronymConstructorDouble = mockConstruction(TeacherAcronym.class);
              MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class);
              MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
              MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class)
         ) {

             US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                     _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

             String date = "12-03-2024";
             UUID teacherCategory = UUID.randomUUID();
             String teacherAcronym = "ABC";

             when(_teacherServiceDouble.existsById(any(TeacherID.class))).thenReturn(false);

             //Act
             boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                     date, teacherCategory, teacherAcronym);

             //Assert
             assertFalse(result);
         }
     }

     @Test
     void shouldReturnFalseIfTeacherCategoryDoesNotExistWhenCallingUpdateTeacherCategory() {
         //Arrange
         try (MockedConstruction<TeacherAcronym> acronymConstructorDouble = mockConstruction(TeacherAcronym.class);
              MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class);
              MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
              MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class)
         ) {

             US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                     _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

             String date = "12-03-2024";
             UUID teacherCategory = UUID.randomUUID();
             String teacherAcronym = "ABC";

             when(_teacherServiceDouble.existsById(any(TeacherID.class))).thenReturn(true);
             when(_teacherCategoryServiceDouble.existsById(any(TeacherCategoryID.class))).thenReturn(false);

             //Act
             boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                     date, teacherCategory, teacherAcronym);

             //Assert
             assertFalse(result);
         }
     }

     @Test
     void shouldReturnFalseIfUpdateTeacherCategoryFails() throws Exception {
         //Arrange
         try (MockedConstruction<TeacherAcronym> acronymConstructorDouble = mockConstruction(TeacherAcronym.class);
              MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class);
              MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
              MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class)
         ) {

             US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                     _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

             String date = "12-03-2024";
             UUID teacherCategory = UUID.randomUUID();
             String teacherAcronym = "ABC";

             when(_teacherServiceDouble.existsById(any(TeacherID.class))).thenReturn(true);
             when(_teacherCategoryServiceDouble.existsById(any(TeacherCategoryID.class))).thenReturn(false);
             when(_teacherCareerProgressionServiceDouble.updateTeacherCategoryInTeacherCareerProgression(
                     any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenReturn(false);

             //Act
             boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                     date, teacherCategory, teacherAcronym);

             //Assert
             assertFalse(result);
         }
     }

     @Test
     void shouldReturnFalseIfUpdateTeacherCategoryInServiceThrowsExceptionWhenCallingUpdateTeacherCategoryInController() throws Exception {
         //Arrange
         try (MockedConstruction<TeacherAcronym> acronymConstructorDouble = mockConstruction(TeacherAcronym.class);
              MockedConstruction<TeacherID> teacherIDConstructorDouble = mockConstruction(TeacherID.class);
              MockedConstruction<Date> dateConstructorDouble = mockConstruction(Date.class);
              MockedConstruction<TeacherCategoryID> teacherCategoryIDConstructorDouble = mockConstruction(TeacherCategoryID.class)
         ) {

             US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(
                     _teacherServiceDouble, _teacherCategoryServiceDouble, _teacherCareerProgressionServiceDouble);

             String date = "12-03-2024";
             UUID teacherCategory = UUID.randomUUID();
             String teacherAcronym = "ABC";

             when(_teacherServiceDouble.existsById(any(TeacherID.class))).thenReturn(true);
             when(_teacherCategoryServiceDouble.existsById(any(TeacherCategoryID.class))).thenReturn(false);
             when(_teacherCareerProgressionServiceDouble.updateTeacherCategoryInTeacherCareerProgression(
                     any(Date.class), any(TeacherCategoryID.class), any(TeacherID.class))).thenThrow(Exception.class);

             //Act
             boolean result = controller.updateTeacherCategoryInTeacherCareerProgression(
                     date, teacherCategory, teacherAcronym);

             //Assert
             assertFalse(result);
         }
     }
}

