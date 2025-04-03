package PAI.controller;

import PAI.VOs.TeacherID;
import PAI.domain.CourseEditionDDD;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepositoryDDD;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.Test;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    // RUC correctly defined
    @Test
    void shouldDefineRucForCourseEdition() throws Exception {
        // Arrange
        ICourseEditionRepositoryDDD iCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEditionDDD courseEdition_DDD = mock(CourseEditionDDD.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(courseEdition_DDD.setRuc(teacherID)).thenReturn(true);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEdition_DDD, teacherID);

        // Assert
        assertTrue(result);
    }

    // RUC not defined - null CourseEdition
    @Test
    void shouldNotDefineRucForCourseEditionIfCourseEditionIsNull() throws Exception {
        // Arrange
        ICourseEditionRepositoryDDD iCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        TeacherID teacherID = mock(TeacherID.class);

        // Act
        boolean result = controller.defineRucForCourseEdition(null, teacherID);

        // Assert
        assertFalse(result);
    }

    // RUC not defined - null Teacher
    @Test
    void shouldNotDefineRucForCourseEditionIfTeacherIsNull() throws Exception {
        // Arrange
        ICourseEditionRepositoryDDD iCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEditionDDD courseEdition_DDD = mock(CourseEditionDDD.class);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEdition_DDD, null);

        // Assert
        assertFalse(result);
    }

    // Teste getAllTeachers
    @Test
    void shouldReturnAllTeachersRegisteredInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositoryDDD iCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        List<Teacher> teachers = List.of(teacher1, teacher2);

        when(iTeacherRepository.findAll()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = controller.getAllTeachers();

        // Assert
        assertIterableEquals(teachers, result);
    }

    // Teste getCourseEditions
    @Test
    void shouldReturnAllCourseEditionsRegisteredInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepositoryDDD iCourseEditionRepository = mock(ICourseEditionRepositoryDDD.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEditionDDD courseEdition1 = mock(CourseEditionDDD.class);
        CourseEditionDDD courseEdition2 = mock(CourseEditionDDD.class);

        List<CourseEditionDDD> courseEditions = List.of(courseEdition1, courseEdition2);

        when(iCourseEditionRepository.findAll()).thenReturn(courseEditions);

        // Act
        Iterable<CourseEditionDDD> result = controller.getAllCourseEditions();

        // Assert
        assertIterableEquals(courseEditions, result);
    }
}
