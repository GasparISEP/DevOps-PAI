package PAI.controller;

import PAI.domain.CourseEdition;
import PAI.domain.Teacher;
import PAI.factory.CourseEditionFactoryImpl;
import PAI.factory.CourseEditionListFactoryImpl;
import PAI.factory.TeacherFactory;
import PAI.factory.TeacherListFactory;
import PAI.repository.CourseEditionRepository;
import PAI.repository.TeacherRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    @Test
    void shouldSuccessfullyDefineRucForCourseEdition() throws Exception {
        // Arrange
        CourseEditionRepository repo1 = mock(CourseEditionRepository.class);
        TeacherRepository repo2 = mock(TeacherRepository.class);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        CourseEdition cE1 = mock(CourseEdition.class);
        Teacher t1 = mock(Teacher.class);

        when(repo1.setRucInACourseEdition(cE1, t1)).thenReturn(true);

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, t1);

        // Assert
        assertTrue(result, "RUC definition should succeed");
        verify(repo1).setRucInACourseEdition(cE1, t1); // Verify that the repository method was called



//        // Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//
//        // Arrange Teacher Ruc
//        Teacher t1 = mock(Teacher.class);
//
//        // Arrange CourseEdition
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        Teacher teacher = mock(Teacher.class);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = mock(ProgrammeCourseListFactoryImpl.class);
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
//        StudyPlanListFactoryImpl studyPlanArrayListFactory = mock(StudyPlanListFactoryImpl.class);
//        StudyPlanFactoryImpl studyPlanFactory = mock(StudyPlanFactoryImpl.class);
//        CourseFactory courseFactory = mock(CourseFactory.class);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
//        Course c1 = new Course("Informatics", "INF", 6, 1);
//        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
//        CourseEdition cE1 = new CourseEdition(c1, pE1);
//
//        repo1.createAndSaveCourseEdition(c1, pE1);
//
//        // Act
//        boolean result = ctrl1.defineRucForCourseEdition(cE1, t1);
//
//        // Assert
//        assertTrue(result, "RUC definition should succeed");
   }

    @Test
    void shouldNotRedefineRucIfRucAlreadyExists() throws Exception {

        // Arrange
        CourseEditionRepository repo1 = mock(CourseEditionRepository.class);
        TeacherRepository repo2 = mock(TeacherRepository.class);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        CourseEdition cE1 = mock(CourseEdition.class);
        Teacher t1 = mock(Teacher.class);

        when(repo1.setRucInACourseEdition(cE1, t1)).thenReturn(false);

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, t1);

        // Assert
        assertFalse(result, "RUC should not be redefined if it already exists");
        verify(repo1).setRucInACourseEdition(cE1, t1);  // Verify that the repository method was called

//        // Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        Teacher teacher = mock(Teacher.class);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = mock(ProgrammeCourseListFactoryImpl.class);
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
//        StudyPlanListFactoryImpl studyPlanArrayListFactory = mock(StudyPlanListFactoryImpl.class);
//        StudyPlanFactoryImpl studyPlanFactory = mock(StudyPlanFactoryImpl.class);
//        CourseFactory courseFactory = mock(CourseFactory.class);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
//        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
//
//        Course c1 = new Course("Informatics", "INF", 6, 1);
//        repo1.createAndSaveCourseEdition(c1, pE1);
//
//        CourseEdition cE1 = new CourseEdition(c1, pE1);
//        ctrl1.defineRucForCourseEdition(cE1, teacher);  // First RUC definition
//
//        // Act
//        boolean result = ctrl1.defineRucForCourseEdition(cE1, teacher);  // Trying again
//
//        // Assert
//        assertFalse(result, "RUC should not be defined again if it already exists");
    }

    @Test
    void shouldReturnTwoCourseEditionsWhenTwoAreRegistered() throws Exception {

        // Arrange
        CourseEditionRepository repo1 = mock(CourseEditionRepository.class);
        TeacherRepository repo2 = mock(TeacherRepository.class);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        CourseEdition cE1 = mock(CourseEdition.class);
        CourseEdition cE2 = mock(CourseEdition.class);
        List<CourseEdition> courseEditions = List.of(cE1, cE2);

        when(repo1.getCourseEditions()).thenReturn(courseEditions);

        // Act
        List<CourseEdition> result = ctrl1.getCourseEditions();

        // Assert
        assertEquals(2, result.size(), "Should return exactly two course editions");
        verify(repo1).getCourseEditions(); // Ensure repository method was called


//        //Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//
//
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        Teacher teacher = mock(Teacher.class);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = mock(ProgrammeCourseListFactoryImpl.class);
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
//        StudyPlanListFactoryImpl studyPlanArrayListFactory = mock(StudyPlanListFactoryImpl.class);
//        StudyPlanFactoryImpl studyPlanFactory = mock(StudyPlanFactoryImpl.class);
//        CourseFactory courseFactory = mock(CourseFactory.class);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
//
//
//        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
//
//        //Arrange CourseEdition
//        Course c1 = new Course ("Informatics", "INF", 6, 1);
//        repo1.createAndSaveCourseEdition(c1, pE1);
//
//        Course c2 = new Course ("Cidadania", "CID", 6, 1);
//        repo1.createAndSaveCourseEdition(c2, pE1);
//
//        //Act
//
//        List<CourseEdition> result = ctrl1.getCourseEditions();
//
//        //Assert
//        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAllTeachersRegisteredInTheRepository() throws Exception {

        // Arrange
        CourseEditionRepository repo1 = mock(CourseEditionRepository.class);
        TeacherRepository repo2 = mock(TeacherRepository.class);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);
        List<Teacher> teachers = List.of(teacher1, teacher2);

        when(repo2.getAllTeachers()).thenReturn(teachers);

        // Act
        List<Teacher> result = ctrl1.getTeachers();

        // Assert
        assertEquals(2, result.size(), "Should return exactly two teachers");
        verify(repo2).getAllTeachers(); // Verify method was called

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
    }

    @Test
    void shouldNotDefineRucForCourseEditionIfTeacherIsNull() throws Exception {

        // Arrange
        CourseEditionRepository repo1 = mock(CourseEditionRepository.class);
        TeacherRepository repo2 = mock(TeacherRepository.class);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        CourseEdition cE1 = mock(CourseEdition.class);

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, null);

        // Assert
        assertFalse(result, "RUC definition should fail when Teacher is null");
        verify(repo1, never()).setRucInACourseEdition(any(), any()); // Ensure repository is NOT called

//        // Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//
//        // Arrange Teacher and CourseEdition setup
//        DegreeType master = new DegreeType("Master", 240);
//        Department CSE = new Department("CSE", "Computer Science Engineer");
//        Teacher teacher = mock(Teacher.class);
//        ProgrammeCourseListFactoryImpl programmeCourseListFactory = mock(ProgrammeCourseListFactoryImpl.class);
//        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = mock(CourseInStudyPlanFactoryImpl.class);
//        StudyPlanListFactoryImpl studyPlanArrayListFactory = mock(StudyPlanListFactoryImpl.class);
//        StudyPlanFactoryImpl studyPlanFactory = mock(StudyPlanFactoryImpl.class);
//        CourseFactory courseFactory = mock(CourseFactory.class);
//
//        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory, courseInStudyPlanFactory ,studyPlanArrayListFactory, studyPlanFactory, courseFactory);
//        Course c1 = new Course("Informatics", "INF", 6, 1);
//        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
//        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
//        CourseEdition cE1 = new CourseEdition(c1, pE1);
//
//        // Register the CourseEdition in the repository
//        repo1.createAndSaveCourseEdition(c1, pE1); // Ensure the CourseEdition is added to the repository
//
//        // Act
//        boolean result = ctrl1.defineRucForCourseEdition(cE1, null); // Passing null as Teacher
//
//        // Assert
//        assertFalse(result, "RUC definition should fail when Teacher is null"); // Expect false when Teacher is null
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIsNull() throws Exception {

        // Arrange
        // Initialize factory objects
        CourseEditionFactoryImpl courseEditionFactoryImpl = new CourseEditionFactoryImpl();
        CourseEditionListFactoryImpl courseEditionListFactoryImpl = new CourseEditionListFactoryImpl();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactoryImpl, courseEditionListFactoryImpl);

        // Initialize TeacherRepository and its dependencies
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);

        // Create controller with repositories
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        // Mock Teacher
        Teacher t1 = mock(Teacher.class);

        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> ctrl1.defineRucForCourseEdition(null, t1)); // Expect IllegalArgumentException for null CourseEdition

//        // Arrange
//        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
//        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
//        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
//        TeacherFactory teacherFactory = mock(TeacherFactory.class);
//        TeacherListFactory teacherListFactory = mock(TeacherListFactory.class);
//        TeacherRepository repo2 = new TeacherRepository(teacherFactory, teacherListFactory);
//        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
//        Teacher t1 = mock(Teacher.class);
//        // Act + Assert
//        assertThrows(IllegalArgumentException.class, () -> ctrl1.defineRucForCourseEdition(null, t1));
    }
}
