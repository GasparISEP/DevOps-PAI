package PAI.controller;
import PAI.domain.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
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
    private static TeacherCategoryRepositoryFactory teacherCategoryRepositoryFactory;
    private static TeacherRepositoryFactory teacherRepositoryFactory;
    private static TeacherFactory teacherFactory;

    @BeforeAll
    static void setUp() throws Exception {

        teacherCategoryRepositoryFactory = mock(TeacherCategoryRepositoryFactory.class);
        teacherRepositoryFactory = mock(TeacherRepositoryFactory.class);

        tcr1 = mock(TeacherCategoryRepository.class);
        tr1 = mock(TeacherRepository.class);

        when(teacherCategoryRepositoryFactory.newTeacherCategoryRepository()).thenReturn(tcr1);
        when(teacherRepositoryFactory.newTeacherRepository(teacherFactory)).thenReturn(tr1);

    }

    @Test
    void shouldCreateUpdateTeachersCategoryController() {
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(teacherRepositoryFactory, teacherCategoryRepositoryFactory, teacherFactory);
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
        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(teacherRepositoryFactory, teacherCategoryRepositoryFactory, teacherFactory);
        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory(date, teacherNIF, teacherCategoryName));
    }

    @Test
    void successfullyUpdatedTeachersCategory() throws Exception {

        TeacherCategory tc1 = new TeacherCategory("Assistente");
        TeacherCategory tc2 = new TeacherCategory("Efectivo");

        when(tcr1.getTeacherCategoryByName("Assistente")).thenReturn(Optional.of(tc1));
        when(tcr1.getTeacherCategoryByName("Efectivo")).thenReturn(Optional.of(tc2));


        Teacher teacher = new Teacher("EDC", "Eugénio Cardoso", "edc@isep.ipp.pt", "213784542", "B106", "Mestrado Engenharia, ISEP",
                "Rua da Pedra", "4300-020", "Porto", "Portugal", "28-01-2025", tc1, 75, new Department("ENC", "Engenharia Civil"));

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacher));

        when(teacherCategoryRepositoryFactory.newTeacherCategoryRepository()).thenReturn(tcr1);
        when(teacherRepositoryFactory.newTeacherRepository(teacherFactory)).thenReturn(tr1);

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(teacherRepositoryFactory, teacherCategoryRepositoryFactory, teacherFactory);

        boolean result = controller1.updateTeacherCategory("30-01-2025", "213784542", "Efectivo");

        assertTrue(result);
    }

    @Test
    void noTeacherInRepoWithInputNIF_UnsuccessfullyUpdatedTeachersCategory() {

        when(tr1.getTeacherByNIF("111111111")).thenReturn(Optional.empty());

        when(teacherCategoryRepositoryFactory.newTeacherCategoryRepository()).thenReturn(tcr1);
        when(teacherRepositoryFactory.newTeacherRepository(teacherFactory)).thenReturn(tr1);

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(teacherRepositoryFactory, teacherCategoryRepositoryFactory, teacherFactory);

        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "111111111", "Efectivo"));
    }

    @Test
    void noTeacherCategoryInRepoWithInputTeacherCategoryName_UnsuccessfullyUpdateddTeachersCategory() throws Exception {

        TeacherCategory tc1 = new TeacherCategory("Assistente");

        Teacher teacher = new Teacher("EDC", "Eugénio Cardoso", "edc@isep.ipp.pt", "213784542", "B106", "Mestrado Engenharia, ISEP",
                "Rua da Pedra", "4300-020", "Porto", "Portugal", "28-01-2025", tc1, 75, new Department("ENC", "Engenharia Civil"));

        when(tr1.getTeacherByNIF("213784542")).thenReturn(Optional.of(teacher));
        when(tcr1.getTeacherCategoryByName("Doutor")).thenReturn(Optional.empty());

        US14_UpdateTeachersCategoryController controller1 = new US14_UpdateTeachersCategoryController(teacherRepositoryFactory, teacherCategoryRepositoryFactory, teacherFactory);

        assertThrows(IllegalArgumentException.class, () -> controller1.updateTeacherCategory("30-01-2025", "213784542", "Doutor"));
    }
}