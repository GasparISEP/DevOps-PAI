package PAI.controller;

import PAI.domain.*;
import PAI.factory.*;
import PAI.repository.CourseRepository;
import PAI.repository.ProgrammeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class US03AddCourseToProgrammeControllerTest {

    private US03_AddCourseToProgrammeController us03AddCourseToProgrammeController;
    private ProgrammeRepository programmeRepositoryDouble;
    private CourseRepository courseRepositoryDouble;

    @BeforeEach
    void setUp() throws Exception {
        programmeRepositoryDouble = mock(ProgrammeRepository.class);
        courseRepositoryDouble = mock(CourseRepository.class);
        us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeRepositoryDouble, courseRepositoryDouble);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList_IsolatedTest() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);

        when(programmeDouble.addCourseToAProgramme(courseDouble)).thenReturn(false);

        // act
        boolean result = us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        Course courseDouble = mock(Course.class);
        when(programmeDouble.addCourseToAProgramme(courseDouble)).thenReturn(true);
        //act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, courseDouble);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeListIsNull_IsolatedTest() throws IllegalArgumentException {
        // arrange
        ProgrammeRepository nullProgrammeList = null;
        CourseRepository courseRepository = mock(CourseRepository.class);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(nullProgrammeList, courseRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseIsNull_IsolatedTest() {
        // arrange
        Programme programmeDouble = mock(Programme.class);
        //act + assert
        assertThrows(Exception.class, () -> {
            us03AddCourseToProgrammeController.addCourseToProgramme(programmeDouble, null);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeIsNull_IsolatedTest() {
        // arrange
        Course courseDouble = mock(Course.class);
        //act + assert
        assertThrows(Exception.class, () -> {
            us03AddCourseToProgrammeController.addCourseToProgramme(null, courseDouble);
        });
    }


    @Test
    void shouldReturnAllProgrammes_IsolatedTest() {
        // arrange
        List<Programme> programmeList = mock(List.class);

        when(programmeRepositoryDouble.getAllProgrammes()).thenReturn(programmeList);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programmeList, result);
    }

    @Test
    void shouldReturnAllCourses_IsolatedTest() {
        // arrange
        List<Course> courseListDouble = mock(List.class);
        when(courseRepositoryDouble.getAllCourses()).thenReturn(courseListDouble);
        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();
        // assert
        assertEquals(courseListDouble, result);
    }

    @Test
    void shouldThrowExceptionIfCourseRepositoryIsNull_IsolatedTest() {
        // arrange
        ProgrammeRepository programmeList = mock(ProgrammeRepository.class);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeList, null);
        });
    }

    // Integration Tests

    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }


    @Test
    void shouldNotAddCourseToProgrammeIfCourseAlreadyInList() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        Programme lei = programmeList.getAllProgrammes().get(0);
        lei.addCourseToAProgramme(courseRepository.getAllCourses().get(0));
        Course course1 = lei.getCourseList().get(0);
        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(lei, course1);
        // assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldAddCourseToProgrammeIfAllArgumentsAreValid() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        CourseFactory courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        Course course1 = courseRepository.getAllCourses().get(0);
        Programme lei = programmeList.getAllProgrammes().get(0);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(lei, course1);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeListIsNull() throws Exception {
        // arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, courseRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseRepositoryIsNull() throws IllegalArgumentException {
        // arrange
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeList, null);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseIsNull() throws Exception {
        // arrange
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);

        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);

        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        Programme lei = programmeList.getAllProgrammes().get(0);
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        //act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(lei, null);
        });
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneCourseInList() throws Exception {
        // arrange
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);

        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);

        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnCourseInList() throws Exception {
        // arrange
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        Course course = courseRepository.getAllCourses().get(0);
        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);
        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(course, result.get(0));
    }

    @Test
    void shouldReturnSizeOneIfOnlyOneProgrammeInList() throws Exception {
        // arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);

        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(1, result.size());
    }

    @Test
    void shouldReturnProgrammeInList() throws Exception {
        // arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);

        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        Programme programme = programmeList.getAllProgrammes().get(0);
        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programme, result.get(0));
    }

    @Test
    void shouldReturnAllProgrammes() throws Exception {
        // arrange
        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        Department department1 = new Department("DEI", "Departamento EI");
        TeacherCategory teacherCategory1 = new TeacherCategory("categoria1");
        AddressFactoryImpl addressFactory = new AddressFactoryImpl();
        TeacherCareerProgressionFactory teacherCareerProgressionFactory = new TeacherCareerProgressionFactory();
        TeacherCareerProgressionListFactory teacherCareerProgressionListFactory = new TeacherCareerProgressionListFactory();
        DegreeType degree1 = new DegreeType("Licenciatura",30);
        Teacher teacher1 = new Teacher("NSS", "Nuno Silva", "NSS@isep.ipp.pt", "238310710",
                "A123","Doutoramento em Engenharia Informatica, 2005, " +
                "ISEP","Rua São Tomé Nº100", "4435-696","Gondomar","Portugal",
                addressFactory, "20-12-2010", teacherCategory1, 100, department1, teacherCareerProgressionFactory, teacherCareerProgressionListFactory);

        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);
        ProgrammeCourseListFactoryImpl programmeCourseListFactoryImpl1 = new ProgrammeCourseListFactoryImpl();
        CourseInStudyPlanFactoryImpl courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        StudyPlanListFactoryImpl studyPlanArrayListFactory = new StudyPlanListFactoryImpl();
        StudyPlanFactoryImpl studyPlanFactory = new StudyPlanFactoryImpl();
        programmeList.registerProgramme("Engenharia Informática", "LEI", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);
        programmeList.registerProgramme("Engenharia Química", "LEQ", 30, 2,
                degree1, department1, teacher1, programmeCourseListFactoryImpl1,courseInStudyPlanFactory, studyPlanArrayListFactory, studyPlanFactory, courseFactoryImpl);

        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);

        // act
        List<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(2, result.size());
        assertEquals(programmeList.getAllProgrammes().get(0), result.get(0));
        assertEquals(programmeList.getAllProgrammes().get(1), result.get(1));
    }

    @Test
    void shouldReturnAllCourses() throws Exception {
        // arrange
        ProgrammeFactoryImpl programmeFactory = new ProgrammeFactoryImpl();
        ProgrammeRepositoryListFactoryImpl programmeListArrayListFactory = new ProgrammeRepositoryListFactoryImpl();
        ProgrammeRepository programmeList = new ProgrammeRepository(programmeFactory, programmeListArrayListFactory);

        CourseFactoryImpl courseFactoryImpl = new CourseFactoryImpl();
        CourseListFactory courseListFactory = new CourseListFactory();
        CourseRepository courseRepository = new CourseRepository(courseFactoryImpl, courseListFactory);
        courseRepository.registerCourse("matemática", "MTA", 5, 1);
        courseRepository.registerCourse("algebra", "ALG", 5, 1);
        US03_AddCourseToProgrammeController us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(programmeList, courseRepository);

        // act
        List<Course> result = us03AddCourseToProgrammeController.getAllCourses();

        // assert
        assertEquals(2, result.size());
        assertEquals(courseRepository.getAllCourses().get(0), result.get(0));
        assertEquals(courseRepository.getAllCourses().get(1), result.get(1));
    }
}
