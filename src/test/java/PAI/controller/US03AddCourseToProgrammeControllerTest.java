
package PAI.controller;

import PAI.domain.course.Course;
import PAI.service.course.ICourseService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.*;

public class US03AddCourseToProgrammeControllerTest {

    private US03_AddCourseToProgrammeController controller;
    private IProgrammeService programmeService;
    private ICourseService courseService;
    private ICourseInStudyPlanService courseInStudyPlanService;

    @BeforeEach
    void setUp() throws Exception {
        programmeService = mock(IProgrammeService.class);
        courseService = mock(ICourseService.class);
        courseInStudyPlanService = mock(ICourseInStudyPlanService.class);
        controller = new US03_AddCourseToProgrammeController(programmeService, courseService, courseInStudyPlanService);
    }

    @Test
    void shouldThrowExceptionIfProgrammeServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, courseService, courseInStudyPlanService);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeService, null, courseInStudyPlanService);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeService, courseService, null);
        });
    }

    @Test
    void shouldReturnAllCourses() {
        // arrange
        Iterable<Course> expectedCourses = mock(Iterable.class);
        when(courseService.findAll()).thenReturn(expectedCourses);

        // act
        Iterable<Course> actualCourses = controller.getAllCourses();

        // assert
        assertEquals(expectedCourses, actualCourses);
    }


//    @Test
//    void shouldReturnFalseIfNotAddCourseToProgramme_IsolatedTest() throws Exception {
//        // arrange
//        CurricularYear curricularYear = mock(CurricularYear.class);
//        Semester semester = mock(Semester.class);
//        Course course = mock(Course.class);
//        StudyPlan studyPlan = mock(StudyPlan.class);
//        CourseID courseID = mock(CourseID.class);
//        StudyPlanID studyPlanID = mock(StudyPlanID.class);
//
//        when(course.identity()).thenReturn(courseID);
//        when(studyPlan.identity()).thenReturn(studyPlanID);
//        when(iCourseInStudyPlanService.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID)).thenReturn(false);
//
//        // act
//        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
//        //assert
//        assertFalse(addCourseToProgramme);
//    }
//
//    @Test
//    void shouldAddCourseToProgramme() throws Exception {
//        // arrange
//        CurricularYear curricularYear = mock(CurricularYear.class);
//        Semester semester = mock(Semester.class);
//        Course course = mock(Course.class);
//        StudyPlan studyPlan = mock(StudyPlan.class);
//        CourseID courseID = mock(CourseID.class);
//        StudyPlanID studyPlanID = mock(StudyPlanID.class);
//
//        when(course.identity()).thenReturn(courseID);
//        when(studyPlan.identity()).thenReturn(studyPlanID);
//        when(iCourseInStudyPlanService.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID)).thenReturn(true);
//        //act
//        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
//        //assert
//        assertTrue(addCourseToProgramme);
//    }
//
//    @Test
//    void shouldThrowExceptionIfProgrammeRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
//        // arrange
//        // act + assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(null, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfPCourseRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
//        // arrange
//        // act + assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository,null, iStudyPlanRepository, iCourseInStudyPlanService);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfPStudyPlanRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
//        // arrange
//        // act + assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, null, iCourseInStudyPlanService);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfPCourseInStudyPlanRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
//        // arrange
//        // act + assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, null);
//        });
//    }
//
//
//    @Test
//    void shouldReturnAllProgrammes_IsolatedTest() {
//        // arrange
//        List<Programme> programmeList = mock(List.class);
//
//        when(iProgrammeRepository.findAll()).thenReturn(programmeList);
//
//        // act
//        Iterable<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();
//
//        // assert
//        assertEquals(programmeList, result);
//    }
//
//    @Test
//    void shouldReturnAllCourses_IsolatedTest() {
//        // arrange
//        List<Course> courseListDouble = mock(List.class);
//        when(iCourseRepository.findAll()).thenReturn(courseListDouble);
//        // act
//        Iterable<Course> result = us03AddCourseToProgrammeController.getAllCourses();
//        // assert
//        assertEquals(courseListDouble, result);
//    }
//
//    @Test
//    void shouldReturnAllStudyPlansByProgrammeID_IsolatedTest() {
//        // arrange
//        ProgrammeID programmeID = mock(ProgrammeID.class);
//        List<StudyPlan> studyPlanList = mock(List.class);
//        when(iStudyPlanRepository.getAllStudyPlansByProgrammeId(programmeID)).thenReturn(studyPlanList);
//        // act
//        Iterable<StudyPlan> result = us03AddCourseToProgrammeController.getAllStudyPlansByProgrammeId(programmeID);
//        // assert
//        assertEquals(studyPlanList, result);
//    }
//
//    @Test
//    void shouldThrowIllegalArgumentExceptionIfCourseIsNull_IsolatedTest() throws Exception {
//        // arrange
//        CurricularYear curricularYear = mock(CurricularYear.class);
//        Semester semester = mock(Semester.class);
//        StudyPlan studyPlan = mock(StudyPlan.class);
//        // act & assert
//        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlan);
//        });
//    }
//
//    @Test
//    void shouldThrowIllegalArgumentExceptionIfStudyPlanIsNull_IsolatedTest() throws Exception {
//        // arrange
//        CurricularYear curricularYear = mock(CurricularYear.class);
//        Semester semester = mock(Semester.class);
//        Course course = mock(Course.class);
//        // act & assert
//        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, null);
//        });
//    }
//
//    @Test
//    void shouldThrowIllegalArgumentExceptionIfCurricularYearIsNull_IsolatedTest() throws Exception {
//        // arrange
//        StudyPlan studyPlan = mock(StudyPlan.class);
//        Semester semester = mock(Semester.class);
//        Course course = mock(Course.class);
//        // act & assert
//        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, null, course, studyPlan);
//        });
//    }
//
//    @Test
//    void shouldThrowIllegalArgumentExceptionIfSemesterIsNull_IsolatedTest() throws Exception {
//        // arrange
//        CurricularYear curricularYear = mock(CurricularYear.class);
//        StudyPlan studyPlan = mock(StudyPlan.class);
//        Course course = mock(Course.class);
//        // act & assert
//        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(null , curricularYear, course, studyPlan);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfProgrammeIdIsNull() {
//        assertThrows(IllegalArgumentException.class, () -> {
//            us03AddCourseToProgrammeController.getAllStudyPlansByProgrammeId(null);
//        });
//    }
//
//    // Integration Tests
//
//    @Test
//    void shouldThrowExceptionIfCourseRepositoryNull() throws Exception {
//        // arrange
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        ICourseInStudyPlanListFactory cisplf = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository cispr = new CourseInStudyPlanRepositoryImpl(cisplf);
//        ICourseInStudyPlanFactory cisf = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanService cisService = new CourseInStudyPlanServiceImpl(cispr, cisf);
//
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository, null, iStudyPlanRepository, cisService);
//        });
//    }
//
//
//    @Test
//    void shouldThrowExceptionIfProgrammeRepositoryNull() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        ICourseInStudyPlanListFactory cisplf = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository cispr = new CourseInStudyPlanRepositoryImpl(cisplf);
//        ICourseInStudyPlanFactory cisf = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanService cisService = new CourseInStudyPlanServiceImpl(cispr, cisf);
//
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(null, iCourseRepository, iStudyPlanRepository, cisService);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfStudyPlanRepositoryNull() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanListFactory cisplf = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository cispr = new CourseInStudyPlanRepositoryImpl(cisplf);
//        ICourseInStudyPlanFactory cisf = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanService cisService = new CourseInStudyPlanServiceImpl(cispr, cisf);
//
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, null, cisService);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfCourseInStudyPlanRepositoryNull() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, null);
//        });
//    }
//
//    @Test
//    void shouldCreateAddCourseToProgrammeController() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanListFactory cisplf = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository cispr = new CourseInStudyPlanRepositoryImpl(cisplf);
//        ICourseInStudyPlanFactory cisf = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanService cisService = new CourseInStudyPlanServiceImpl(cispr, cisf);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//        //act
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, cisService);
//        //assert
//        assertNotNull(US03AddCourseToProgrammeController);
//    }
//
//
//    @Test
//    void shouldAddCourseToProgramme_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Name name = new Name("Licenciatura Engenharia Informática");
//        Acronym acronym = new Acronym("LEI");
//        Course course = iCourseFactory.createCourse(name, acronym);
//
//        Semester semester = new Semester(2);
//        CurricularYear curricularYear = new CurricularYear(2);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
//        Acronym acronymProgramme = new Acronym("LEI");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
//        MaxEcts quantEcts = new MaxEcts(30);
//        Date date = new Date("10-10-2022");
//        DurationInYears durationInYears = new DurationInYears(6);
//        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        //act
//        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
//        // assert
//        assertTrue(addCourseToProgramme);
//    }
//
//    @Test
//    void shouldNotAddCourseToProgrammeIfCourseInStudyPlanAlreadyExists_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Name name = new Name("Licenciatura Engenharia Informática");
//        Acronym acronym = new Acronym("LEI");
//        Course course = iCourseFactory.createCourse(name, acronym);
//
//        Semester semester = new Semester(2);
//        CurricularYear curricularYear = new CurricularYear(2);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
//        Acronym acronymProgramme = new Acronym("LEI");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
//        MaxEcts quantEcts = new MaxEcts(30);
//        Date date = new Date("10-10-2022");
//        DurationInYears durationInYears = new DurationInYears(6);
//        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
//        //act
//        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
//        // assert
//        assertFalse(addCourseToProgramme);
//    }
//
//    @Test
//    void shouldNotThrowExceptionIfCourseIsNull_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Semester semester = new Semester(2);
//        CurricularYear curricularYear = new CurricularYear(2);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
//        Acronym acronymProgramme = new Acronym("LEI");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
//        MaxEcts quantEcts = new MaxEcts(30);
//        Date date = new Date("10-10-2022");
//        DurationInYears durationInYears = new DurationInYears(6);
//        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlan);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfStudyPlanIsNull_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Name name = new Name("Licenciatura Engenharia Informática");
//        Acronym acronym = new Acronym("LEI");
//        Course course = iCourseFactory.createCourse(name, acronym);
//
//        Semester semester = new Semester(2);
//        CurricularYear curricularYear = new CurricularYear(2);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, null);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfCurricularYearIsNull_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Name name = new Name("Licenciatura Engenharia Informática");
//        Acronym acronym = new Acronym("LEI");
//        Course course = iCourseFactory.createCourse(name, acronym);
//
//        Semester semester = new Semester(2);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
//        Acronym acronymProgramme = new Acronym("LEI");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
//        MaxEcts quantEcts = new MaxEcts(30);
//        Date date = new Date("10-10-2022");
//        DurationInYears durationInYears = new DurationInYears(6);
//        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            US03AddCourseToProgrammeController.addCourseToProgramme(semester, null, course, studyPlan);
//        });
//    }
//
//    @Test
//    void shouldThrowExceptionIfSemesterIsNull_IntegrationTest() throws Exception {
//        // arrange
//        ICourseRepositoryListFactory iCourseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
//        ICourseFactory iCourseFactory = new CourseFactoryImpl();
//        ICourseRepository iCourseRepository = new CourseRepositoryImpl(iCourseFactory, iCourseRepositoryListFactory);
//
//        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
//        IProgrammeRepositoryListFactory iProgrammeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
//        IProgrammeRepository iProgrammeRepository = new ProgrammeRepositoryImpl(programmeDDDFactory, iProgrammeRepositoryListFactory);
//
//        ICourseInStudyPlanFactory factory = new CourseInStudyPlanFactoryImpl();
//        ICourseInStudyPlanListFactory listFactory = new CourseInStudyPlanListFactoryImpl();
//        ICourseInStudyPlanRepository repo = new CourseInStudyPlanRepositoryImpl(listFactory);
//        ICourseInStudyPlanService iCourseInStudyPlanService = new CourseInStudyPlanServiceImpl(repo, factory);
//
//        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
//        IStudyPlanListFactory iStudyPlanListFactory = new StudyPlanListFactoryImpl();
//        IStudyPlanRepository iStudyPlanRepository = new StudyPlanRepositoryImpl(iStudyPlanFactory, iStudyPlanListFactory);
//
//        Name name = new Name("Licenciatura Engenharia Informática");
//        Acronym acronym = new Acronym("LEI");
//
//        Course course = iCourseFactory.createCourse(name, acronym);
//
//        CurricularYear curricularYear = new CurricularYear(2);
//
//        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
//        Acronym acronymProgramme = new Acronym("LEI");
//        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
//        MaxEcts quantEcts = new MaxEcts(30);
//        Date date = new Date("10-10-2022");
//        DurationInYears durationInYears = new DurationInYears(6);
//        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);
//
//        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
//                new US03_AddCourseToProgrammeController(iProgrammeRepository, iCourseRepository, iStudyPlanRepository, iCourseInStudyPlanService);
//        //act & assert
//        assertThrows(IllegalArgumentException.class, () -> {
//            US03AddCourseToProgrammeController.addCourseToProgramme(null, curricularYear, course, studyPlan);
//        });
//    }
    }