package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

class US14UpdateTeachersCategoryControllerTest {

    private TeacherCategoryRepository tcr1;
    private TeacherRepository tr1;

    @BeforeEach
    void setUp() throws Exception {
        //Create Teacher Categories and add to Repository
        TeacherCategoryFactory doubleTeacherCategoryFactory = mock(TeacherCategoryFactory.class);
        tcr1 = new TeacherCategoryRepository(doubleTeacherCategoryFactory);
        tcr1.registerTeacherCategory("Assistente");
        tcr1.registerTeacherCategory("Efectivo");
        Optional<TeacherCategory> optionalTeacherCategory1 = tcr1.getTeacherCategoryByName("Assistente");
        TeacherCategory tc1 = optionalTeacherCategory1.get();
        //Create Department
        Department dpt1 = new Department("ENC", "Engenharia Civil");
        //Create Teacher with tc1 and add to Repository
        tr1 = new TeacherRepository();
        tr1.registerTeacher("EDC", "Eugénio Cardoso", "edc@isep.ipp.pt", "213784542",
                "B106", "Mestrado Engenharia, ISEP", "Rua da Pedra", "4300-020","Porto",
                "Portugal", "28-01-2025", tc1, 75, dpt1);
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
    void successfullyUpdatedTeachersCategory() {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);
        boolean result = controller1.updateTeacherCategory("30-01-2025", "213784542", "Efectivo");
        assertTrue(result);
    }

    @Test
    void noTeacherInRepoWithInputNIF_UnsuccessfullyUpdatedTeachersCategory() {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);
        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "111111111", "Efectivo"));
    }

    @Test
    void noTeacherCategoryInRepoWithInputTeacherCategoryName_UnsuccessfullyUpdateddTeachersCategory() {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);
        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "213784542", "Doutor"));
    }
}