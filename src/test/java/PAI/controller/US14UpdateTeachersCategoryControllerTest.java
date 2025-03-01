package PAI.controller;
import PAI.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;

import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


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
        Department dp1 = mock(Department.class);
        TeacherCategoryFactory tcf1 = mock(TeacherCategoryFactory.class);
        TeacherCategory tc1 = new TeacherCategory("Assistente");
        TeacherCategory tc2 = new TeacherCategory("Efectivo");
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);

        when(tcf1.createTeacherCategory("Assistente")).thenReturn(tc1);
        when(tcf1.createTeacherCategory("Efectivo")).thenReturn(tc2);

        TeacherFactory t1 = mock(TeacherFactory.class);
        Teacher teacher = mock(Teacher.class);

        when(t1.createTeacher("EDC", "Eugénio Cardoso", "edc@isep.ipp.pt", "213784542", "B106", "Mestrado Engenharia, ISEP",
                "Rua da Pedra", "4300-020", "Porto", "Portugal", "28-01-2025", tc1, 75, dp1, TCPfactoryDouble))
                .thenReturn(teacher);

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacher));
        when(tcr1.getTeacherCategoryByName("Assistente")).thenReturn(Optional.of(tc1));
        when(tcr1.getTeacherCategoryByName("Efectivo")).thenReturn(Optional.of(tc2));

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        // Act
        boolean result = controller1.updateTeacherCategory("30-01-2025", "213784542", "Efectivo");

        // Assert
        assertTrue(result);
    }

    @Test
    void noTeacherInRepoWithInputNIF_UnsuccessfullyUpdatedTeachersCategory() {

        when(tr1.getTeacherByNIF("111111111")).thenReturn(Optional.empty());

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "111111111", "Efectivo"));
    }

    @Test
    void noTeacherCategoryInRepoWithInputTeacherCategoryName_UnsuccessfullyUpdateddTeachersCategory() throws Exception {

        Department dp1 = mock(Department.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
        TeacherCategoryFactory tcf1 = mock(TeacherCategoryFactory.class);
        TeacherCategory tc1 = new TeacherCategory("Assistente");
        when(tcf1.createTeacherCategory("Assistente")).thenReturn(tc1);


        TeacherFactory t1 = mock(TeacherFactory.class);
        Teacher teacher = mock(Teacher.class);
        when(t1.createTeacher("EDC", "Eugénio Cardoso", "edc@isep.ipp.pt", "213784542", "B106", "Mestrado Engenharia, ISEP",
                "Rua da Pedra", "4300-020", "Porto", "Portugal", "28-01-2025", tc1, 75, dp1, TCPfactoryDouble))
                .thenReturn(teacher);

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacher));
        when(tcr1.getTeacherCategoryByName("Doutor")).thenReturn(Optional.empty());

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(tr1, tcr1);

        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "213784542", "Doutor"));
    }
}