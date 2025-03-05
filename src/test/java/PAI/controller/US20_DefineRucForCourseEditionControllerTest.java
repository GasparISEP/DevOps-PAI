package PAI.controller;

import PAI.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class US20_DefineRucForCourseEditionControllerTest {

    @Test
    void shouldSuccessfullyDefineRucForCourseEdition() throws Exception {

        // Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        // Arrange Teacher Ruc
        Teacher t1 = mock(Teacher.class);

        // Arrange CourseEdition
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1, pE1);

        repo1.createAndSaveCourseEdition(c1, pE1);

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, t1);

        // Assert
        assertTrue(result, "RUC definition should succeed");
    }

    @Test
    void shouldNotRedefineRucIfRucAlreadyExists() throws Exception {
        // Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);

        Course c1 = new Course("Informatics", "INF", 6, 1);
        repo1.createAndSaveCourseEdition(c1, pE1);

        CourseEdition cE1 = new CourseEdition(c1, pE1);
        ctrl1.defineRucForCourseEdition(cE1, teacher);  // First RUC definition

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, teacher);  // Trying again

        // Assert
        assertFalse(result, "RUC should not be defined again if it already exists");
    }

    @Test
    void shouldReturnTwoCourseEditionsWhenTwoAreRegistered() throws Exception {

        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);


        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);


        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);

        //Arrange CourseEdition
        Course c1 = new Course ("Informatics", "INF", 6, 1);
        repo1.createAndSaveCourseEdition(c1, pE1);

        Course c2 = new Course ("Cidadania", "CID", 6, 1);
        repo1.createAndSaveCourseEdition(c2, pE1);

        //Act

        List<CourseEdition> result = ctrl1.getCourseEditions();

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldReturnAllTeachersRegisteredInTheRepository() throws Exception {

        //Arrange Teacher
        TeacherCategory category = new TeacherCategory("Professor Adjunto");
        Department department = new Department("MAT", "Mathematics");
        AddressFactory addressFactoryDouble = mock(AddressFactory.class);
        TeacherCareerProgressionFactory TCPfactoryDouble = mock(TeacherCareerProgressionFactory.class);

        //Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);

        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        Teacher teacher1 = mock(Teacher.class);
        Teacher teacher2 = mock(Teacher.class);

        when(teacherFactory.createTeacher("AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
                category, 70, department, TCPfactoryDouble)).thenReturn(teacher1);

        when(teacherFactory.createTeacher("BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
                category, 70, department, TCPfactoryDouble)).thenReturn(teacher2);

        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        repo2.registerTeacher( "AAA", "Joao Costa", "AAA@isep.ipp.pt", "123456789",
                "A106", "Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
                category, 70, department, TCPfactoryDouble);

        repo2.registerTeacher( "BBB", "Mariana Antunes", "BBB@isep.ipp.pt", "123456780",
                "B106","Doutoramento em Engenharia Informatica, 2005, ISEP",
                "Rua das Flores","4444-098","Porto","Portugal", addressFactoryDouble,"15-04-2005",
                category, 70, department, TCPfactoryDouble);

        //Act
        List<Teacher> result = ctrl1.getTeachers();

        //Assert
        assertEquals(2, result.size());
    }

    @Test
    void shouldNotDefineRucForCourseEditionIfTeacherIsNull() throws Exception {

        // Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);

        // Arrange Teacher and CourseEdition setup
        DegreeType master = new DegreeType("Master", 240);
        Department CSE = new Department("CSE", "Computer Science Engineer");
        Teacher teacher = mock(Teacher.class);
        ProgrammeCourseListFactory programmeCourseListFactory = mock(ProgrammeCourseListFactory.class);

        Programme p1 = new Programme("Computer Engineering", "CE", 20, 6, master, CSE, teacher, programmeCourseListFactory);
        Course c1 = new Course("Informatics", "INF", 6, 1);
        SchoolYear sY1 = new SchoolYear("Ano letivo de", "23-11-2024", "09-12-2025");
        ProgrammeEdition pE1 = new ProgrammeEdition(p1, sY1);
        CourseEdition cE1 = new CourseEdition(c1, pE1);

        // Register the CourseEdition in the repository
        repo1.createAndSaveCourseEdition(c1, pE1); // Ensure the CourseEdition is added to the repository

        // Act
        boolean result = ctrl1.defineRucForCourseEdition(cE1, null); // Passing null as Teacher

        // Assert
        assertFalse(result, "RUC definition should fail when Teacher is null"); // Expect false when Teacher is null
    }

    @Test
    void shouldThrowExceptionIfCourseEditionIsNull() throws Exception {

        // Arrange
        CourseEditionFactory courseEditionFactory = new CourseEditionFactory();
        CourseEditionListFactory courseEditionListFactory = new CourseEditionListFactory();
        CourseEditionRepository repo1 = new CourseEditionRepository(courseEditionFactory, courseEditionListFactory);
        TeacherFactory teacherFactory = mock(TeacherFactory.class);
        TeacherRepository repo2 = new TeacherRepository(teacherFactory);
        US20_DefineRucForCourseEditionController ctrl1 = new US20_DefineRucForCourseEditionController(repo1, repo2);
        Teacher t1 = mock(Teacher.class);
        // Act + Assert
        assertThrows(IllegalArgumentException.class, () -> ctrl1.defineRucForCourseEdition(null, t1));
    }
}
