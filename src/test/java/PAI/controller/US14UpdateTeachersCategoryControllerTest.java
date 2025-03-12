package PAI.controller;
import PAI.domain.*;
import PAI.factory.TeacherCategoryFactory;
import PAI.repository.TeacherCategoryRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;
import java.util.stream.Stream;

class US14UpdateTeachersCategoryControllerTest {

    private static TeacherCategoryRepository tcr1;
    private static TeacherRepository tr1;

    @BeforeAll
    static void setUp() throws Exception {

        tcr1 = mock(TeacherCategoryRepository.class);
        tr1 = mock(TeacherRepository.class);

    }

    @Test
    void shouldCreateUpdateTeachersCategoryController() {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);
    }

    static Stream<Arguments> testValues() {
        return Stream.of(
                Arguments.of(" ", "213784542", "Efectivo"),
                Arguments.of("", "213784542", "Efectivo"),
                Arguments.of(null, "213784542", "Efectivo"),
                Arguments.of("30-01-2025", " ", "Efectivo"),
                Arguments.of("30-01-2025", "", "Efectivo"),
                Arguments.of("30-01-2025", null, "Efectivo"),
                Arguments.of("30-01-2025", "213784542", " "),
                Arguments.of("30-01-2025", "213784542", ""),
                Arguments.of("30-01-2025", "213784542", null)
        );
    }
    @ParameterizedTest
    @MethodSource("testValues")
    void inputsAreNullOrBlank_UnsuccessfullyUpdatedCategory(String date, String teacherNIF, String teacherCategoryName) {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);
        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory(date, teacherNIF, teacherCategoryName));
    }

    @Test
    void successfullyUpdatedTeachersCategory() throws Exception {
        // Arrange
        Teacher teacherDouble = mock(Teacher.class);
        TeacherCategory tc1Double = mock(TeacherCategory.class);
        TeacherCategory tc2Double = mock(TeacherCategory.class);

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacherDouble));
        when(teacherDouble.hasThisNIF("213784542")).thenReturn(true);

        when(tc1Double.getName()).thenReturn("Assistente");
        when(tc2Double.getName()).thenReturn("Efetivo");

        when(tcr1.getTeacherCategoryByName("Assistente")).thenReturn(Optional.of(tc1Double));

        when(tcr1.getTeacherCategoryByName("Efetivo")).thenReturn(Optional.of(tc2Double));

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        // Act
        boolean result = controller.updateTeacherCategory("30-01-2025", "213784542", "Efetivo");

        // Assert
        assertTrue(result);
    }

    @Test
    void noTeacherInRepoWithInputNIF_UnsuccessfullyUpdatedTeachersCategory() {
        // Arrange

        when(tr1.getTeacherByNIF("111111111")).thenReturn(Optional.empty());

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategory("30-01-2025", "111111111", "Efectivo"));
    }

    @Test
    void noTeacherCategoryInRepoWithInputTeacherCategoryName_UnsuccessfullyUpdatedTeachersCategory() throws Exception {
        // Arrange
        Teacher teacher = mock(Teacher.class);

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacher));

        when(tcr1.getTeacherCategoryByName("Doutor")).thenReturn(Optional.empty());

        US14_UpdateTeachersCategoryController controller = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> controller.updateTeacherCategory("30-01-2025", "213784542", "Doutor"));
    }
}