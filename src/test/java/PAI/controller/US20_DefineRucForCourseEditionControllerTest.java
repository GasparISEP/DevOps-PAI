package PAI.controller;

import PAI.VOs.TeacherID;
import PAI.domain.CourseEdition_2;
import PAI.domain.Teacher;
import PAI.repository.ICourseEditionRepository;
import PAI.repository.ITeacherRepository;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;
<<<<<<< Updated upstream
import java.util.stream.StreamSupport;

=======
>>>>>>> Stashed changes
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    // RUC correctly defined
    @Test
    void shouldDefineRucForCourseEdition() throws Exception {
        // Arrange
        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEdition_2 courseEdition_2 = mock(CourseEdition_2.class);
        TeacherID teacherID = mock(TeacherID.class);

        when(courseEdition_2.setRuc(teacherID)).thenReturn(true);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEdition_2, teacherID);

        // Assert
        assertTrue(result);
    }

    // RUC not defined - null CourseEdition
    @Test
    void shouldNotDefineRucForCourseEditionIfCourseEditionIsNull() throws Exception {
        // Arrange
        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
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
        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEdition_2 courseEdition_2 = mock(CourseEdition_2.class);

        // Act
        boolean result = controller.defineRucForCourseEdition(courseEdition_2, null);

        // Assert
        assertFalse(result);
    }

    // Teste getAllTeachers
    @Test
    void shouldReturnAllTeachersRegisteredInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        List<Teacher> teachers = List.of(teacher1, teacher2);

<<<<<<< Updated upstream
        when(ctrl1.getTeachers()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = ctrl1.getTeachers();
        List<Teacher> resultList = StreamSupport.stream(result.spliterator(), false).toList();

        // Assert
        assertEquals(2, resultList.size(), "Should return exactly two teachers");

        //Arrange Teacher
//        TeacherCategory category = new TeacherCategory("Professor Adjunto");
//        Department department = new Department("MAT", "Mathematics");
//        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
//        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);
//
//        //Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        Teacher teacher1 = mock(Teacher.class);
//        Teacher teacher2 = mock(Teacher.class);
//
//        when(teacherFactory.createTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
//                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
//                category, 70, department, TCPfactoryDouble)).thenReturn(teacher1);
//
//        when(teacherFactory.createTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
//                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
//                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
//                category, 70, department, TCPfactoryDouble)).thenReturn(teacher2);
//
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//
//        repo2.registerTeacher( "AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
//                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
//                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
//                category, 70, department, TCPfactoryDouble);
//
//        repo2.registerTeacher( "BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
//                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
//                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
//                category, 70, department, TCPfactoryDouble);
//
//        //Act
//        List<Teacher> result = ctrl1.getTeachers();
//
//        //Assert
//        assertEquals(2, result.size());
=======
        when(iTeacherRepository.findAll()).thenReturn(teachers);

        // Act
        Iterable<Teacher> result = controller.getAllTeachers();

        // Assert
        assertIterableEquals(teachers, result);
>>>>>>> Stashed changes
    }

    // Teste getCourseEditions
    @Test
    void shouldReturnAllCourseEditionsRegisteredInTheRepository() throws Exception {
        // Arrange
        ICourseEditionRepository iCourseEditionRepository = mock(ICourseEditionRepository.class);
        ITeacherRepository iTeacherRepository = mock(ITeacherRepository.class);
        US20_DefineRucForCourseEditionController controller = new US20_DefineRucForCourseEditionController(iCourseEditionRepository, iTeacherRepository);

        CourseEdition_2 courseEdition1 = mock(CourseEdition_2.class);
        CourseEdition_2 courseEdition2 = mock(CourseEdition_2.class);

        List<CourseEdition_2> courseEditions = List.of(courseEdition1, courseEdition2);

        when(iCourseEditionRepository.findAll()).thenReturn(courseEditions);

        // Act
        Iterable<CourseEdition_2> result = controller.getAllCourseEditions();

        // Assert
        assertIterableEquals(courseEditions, result);
    }
}

