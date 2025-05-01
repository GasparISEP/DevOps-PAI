
package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.domain.course.ICourseFactory;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanFactoryImpl;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.programme.Programme;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.factory.IProgrammeFactory;
import PAI.factory.ProgrammeFactoryImpl;
import PAI.persistence.mem.courseInStudyPlan.CourseInStudyPlanListFactoryImpl;
import PAI.persistence.mem.courseInStudyPlan.CourseInStudyPlanRepositoryImpl;
import PAI.persistence.mem.courseInStudyPlan.ICourseInStudyPlanListFactory;
import PAI.persistence.mem.courseRepository.CourseRepositoryImpl;
import PAI.persistence.mem.courseRepository.CourseRepositoryListFactoryImpl;
import PAI.persistence.mem.courseRepository.ICourseRepositoryListFactory;
import PAI.persistence.mem.programme.IProgrammeRepositoryListFactory;
import PAI.persistence.mem.programme.ProgrammeRepositoryImpl;
import PAI.persistence.mem.programme.ProgrammeRepositoryListFactoryImpl;
import PAI.persistence.mem.studyPlan.IStudyPlanListFactory;
import PAI.persistence.mem.studyPlan.StudyPlanListFactoryImpl;
import PAI.persistence.mem.studyPlan.StudyPlanRepositoryImpl;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
import PAI.repository.courseRepository.ICourseRepository;
import PAI.repository.programmeRepository.IProgrammeRepository;
import PAI.repository.studyPlanRepository.IStudyPlanRepository;
import PAI.service.course.CourseServiceImpl;
import PAI.service.courseInStudyPlan.CourseInStudyPlanServiceImpl;
import PAI.service.programme.ProgrammeServiceImpl;
import PAI.service.studyPlan.IStudyPlanService;
import PAI.service.course.ICourseService;
import PAI.service.courseInStudyPlan.ICourseInStudyPlanService;
import PAI.service.programme.IProgrammeService;
import PAI.service.studyPlan.StudyPlanServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class US03AddCourseToProgrammeControllerTest {

    private US03_AddCourseToProgrammeController controller;
    private IProgrammeService programmeServiceDouble;
    private ICourseService courseServiceDouble;
    private IStudyPlanService studyPlanServiceDouble;
    private ICourseInStudyPlanService courseInStudyPlanServiceDouble;

    @BeforeEach
    void setUp() throws Exception {
        programmeServiceDouble = mock(IProgrammeService.class);
        courseServiceDouble = mock(ICourseService.class);
        studyPlanServiceDouble = mock(IStudyPlanService.class);
        courseInStudyPlanServiceDouble = mock(ICourseInStudyPlanService.class);
        controller = new US03_AddCourseToProgrammeController(programmeServiceDouble, courseServiceDouble, studyPlanServiceDouble, courseInStudyPlanServiceDouble);
    }

    @Test
    void shouldThrowExceptionIfProgrammeServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, courseServiceDouble, studyPlanServiceDouble, courseInStudyPlanServiceDouble);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeServiceDouble, null, studyPlanServiceDouble, courseInStudyPlanServiceDouble);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeServiceDouble, courseServiceDouble, studyPlanServiceDouble, null);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanServiceIsNull() {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(programmeServiceDouble, courseServiceDouble, null, courseInStudyPlanServiceDouble);
        });
    }

    @Test
    void shouldReturnAllCourses() {
        // arrange
        Iterable<Course> expectedCourses = mock(Iterable.class);
        when(courseServiceDouble.findAll()).thenReturn(expectedCourses);

        // act
        Iterable<Course> actualCourses = controller.getAllCourses();

        // assert
        assertEquals(expectedCourses, actualCourses);
    }

    @Test
    void shouldReturnAllProgrammes() {
        // arrange
        Iterable<Programme> expectedProgrammes = mock(Iterable.class);
        when(programmeServiceDouble.findAll()).thenReturn(expectedProgrammes);

        // act
        Iterable<Programme> actualProgrammes = controller.getAllProgrammes();

        // assert
        assertEquals(expectedProgrammes, actualProgrammes);
    }

    @Test
    void shouldThrowExceptionIfProgrammeIdIsNull() {
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            controller.getLatestStudyPlanByProgrammeId(null);
        });
    }

    @Test
    void shouldReturnLatestStudyPlanByProgrammeID() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        StudyPlanID expectedStudyPlanID = mock(StudyPlanID.class);
        when(studyPlanServiceDouble.getLatestStudyPlanIDByProgrammeID(programmeID)).thenReturn(expectedStudyPlanID);

        // act
        StudyPlanID actualStudyPlanID = controller.getLatestStudyPlanByProgrammeId(programmeID);

        // assert
        assertEquals(expectedStudyPlanID, actualStudyPlanID);
    }

    @Test
    void shouldReturnTrueIfCourseIsAddedToProgrammeSuccessfully() throws Exception {
        // arrange
        Course course = mock(Course.class);
        CourseID courseID = mock(CourseID.class);
        when(course.identity()).thenReturn(courseID);

        StudyPlan studyPlan = mock(StudyPlan.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlan.identity()).thenReturn(studyPlanID);

        int semesterInt = 1;
        int curricularYearInt = 1;
        int durationOfCourseInCurricularYear = 1;
        double quantEctsDouble = 30.0;

        Semester semester = new Semester(semesterInt);
        CurricularYear curricularYear = new CurricularYear(curricularYearInt);
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(durationOfCourseInCurricularYear);
        CourseQuantityCreditsEcts quantEcts = new CourseQuantityCreditsEcts(quantEctsDouble);

        when(courseInStudyPlanServiceDouble.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, duration, quantEcts)).thenReturn(true);

        // act
        boolean result = controller.addCourseToProgramme(semesterInt, curricularYearInt, course, studyPlan, durationOfCourseInCurricularYear, quantEctsDouble);

        // assert
        assertEquals(true, result);
    }

    @Test
    void shouldReturnFalseIfCourseIsNotAddedToProgramme() throws Exception {
        // arrange
        Course course = mock(Course.class);
        CourseID courseID = mock(CourseID.class);
        when(course.identity()).thenReturn(courseID);

        StudyPlan studyPlan = mock(StudyPlan.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        when(studyPlan.identity()).thenReturn(studyPlanID);

        int semesterInt = 1;
        int curricularYearInt = 1;
        int durationOfCourseInCurricularYear = 1;
        double quantEctsDouble = 30.0;

        Semester semester = new Semester(semesterInt);
        CurricularYear curricularYear = new CurricularYear(curricularYearInt);
        DurationCourseInCurricularYear duration = new DurationCourseInCurricularYear(durationOfCourseInCurricularYear);
        CourseQuantityCreditsEcts quantEcts = new CourseQuantityCreditsEcts(quantEctsDouble);

        when(courseInStudyPlanServiceDouble.createCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, duration, quantEcts)).thenReturn(false);

        // act
        boolean result = controller.addCourseToProgramme(semesterInt, curricularYearInt, course, studyPlan, durationOfCourseInCurricularYear, quantEctsDouble);

        // assert
        assertEquals(false, result);
    }

    @Test
    void shouldThrowExceptionIfCourseIsNull() {
        // arrange
        StudyPlan studyPlan = mock(StudyPlan.class);
        int semesterInt = 1;
        int curricularYearInt = 1;
        int durationOfCourseInCurricularYear = 1;
        double quantEctsDouble = 30.0;

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addCourseToProgramme(semesterInt, curricularYearInt, null, studyPlan, durationOfCourseInCurricularYear, quantEctsDouble);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanIsNull() {
        // arrange
        Course course = mock(Course.class);
        int semesterInt = 1;
        int curricularYearInt = 1;
        int durationOfCourseInCurricularYear = 1;
        double quantEctsDouble = 30.0;

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addCourseToProgramme(semesterInt, curricularYearInt, course, null, durationOfCourseInCurricularYear, quantEctsDouble);
        });
    }



    // INTEGRATION TESTS

    private IProgrammeService programmeService;
    private IProgrammeRepository programmeRepository;
    private IProgrammeRepositoryListFactory programmeRepositoryListFactory;
    private IProgrammeFactory programmeFactory;

    private ICourseService courseService;
    private ICourseRepository courseRepository;
    private ICourseRepositoryListFactory courseRepositoryListFactory;
    private ICourseFactory courseFactory;

    private IStudyPlanService studyPlanService;
    private IStudyPlanRepository studyPlanRepository;
    private IStudyPlanListFactory studyPlanRepositoryListFactory;
    private IStudyPlanFactory studyPlanFactory;

    private ICourseInStudyPlanService courseInStudyPlanService;
    private ICourseInStudyPlanRepository courseInStudyPlanRepository;
    private ICourseInStudyPlanListFactory courseInStudyPlanRepositoryListFactory;
    private ICourseInStudyPlanFactory courseInStudyPlanFactory;

    @BeforeEach
    void setUp2() {
        programmeFactory = new ProgrammeFactoryImpl();
        programmeRepositoryListFactory = new ProgrammeRepositoryListFactoryImpl();
        programmeRepository = new ProgrammeRepositoryImpl(programmeRepositoryListFactory);
        programmeService = new ProgrammeServiceImpl(programmeFactory, programmeRepository);

        courseFactory = new CourseFactoryImpl();
        courseRepositoryListFactory = new CourseRepositoryListFactoryImpl();
        courseRepository = new CourseRepositoryImpl(courseRepositoryListFactory);
        courseService = new CourseServiceImpl(courseFactory, courseRepository);

        studyPlanFactory = new StudyPlanFactoryImpl();
        studyPlanRepositoryListFactory = new StudyPlanListFactoryImpl();
        studyPlanRepository = new StudyPlanRepositoryImpl(studyPlanRepositoryListFactory);
        studyPlanService = new StudyPlanServiceImpl(studyPlanRepository, studyPlanFactory);

        courseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        courseInStudyPlanRepositoryListFactory = new CourseInStudyPlanListFactoryImpl();
        courseInStudyPlanRepository = new CourseInStudyPlanRepositoryImpl(courseInStudyPlanRepositoryListFactory);
        courseInStudyPlanService = new CourseInStudyPlanServiceImpl(courseInStudyPlanRepository, courseInStudyPlanFactory);
    }

    @Test
    void shouldCreateUS03AddCourseToProgrammeController() {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        // act + assert
        assertNotNull(controller);
    }

    @Test
    void shouldReturnTrueIfAddCourseToProgrammeSuccessfully() throws Exception {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        Course course = courseFactory.createCourse(name, acronym);

        int semester = 2;
        int curricularYear = 2;
        int duration = 1;
        double quantEcts = 10.0;

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        MaxEcts maxQuantEcts = new MaxEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = studyPlanFactory.createStudyPlan(programmeID, date, durationInYears, maxQuantEcts);

        // act
        boolean result = controller.addCourseToProgramme(semester, curricularYear, course, studyPlan, duration, quantEcts);

        // assert
        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfQuantEctsExceeds30() throws Exception {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        Course course = courseFactory.createCourse(name, acronym);

        int semester = 2;
        int curricularYear = 2;
        int duration = 1;
        double quantEcts = 40.0;

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        MaxEcts maxQuantEcts = new MaxEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = studyPlanFactory.createStudyPlan(programmeID, date, durationInYears, maxQuantEcts);

        // act
        boolean result = controller.addCourseToProgramme(semester, curricularYear, course, studyPlan, duration, quantEcts);

        // assert
        assertFalse(result);
    }

    @Test
    void shouldReturnListOfAllCourses() {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        // act
        Iterable<Course> courses = controller.getAllCourses();

        // assert
        assertNotNull(courses);
    }

    @Test
    void shouldReturnListOfAllProgrammes() {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        // act
        Iterable<Programme> programmes = controller.getAllProgrammes();

        // assert
        assertNotNull(programmes);
    }

    @Test
    void shouldReturnLatestStudyPlanByProgrammeID_Integration() throws Exception {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);
        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);

        MaxEcts maxQuantEcts = new MaxEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = studyPlanFactory.createStudyPlan(programmeID, date, durationInYears, maxQuantEcts);
        studyPlanRepository.save(studyPlan);

        // act
        StudyPlanID studyPlanID = controller.getLatestStudyPlanByProgrammeId(programmeID);

        // assert
        assertNotNull(studyPlanID);
    }

    @Test
    void shouldReturnFalseIfCourseInStudyPlanAlreadyExists() throws Exception {
        // arrange
        US03_AddCourseToProgrammeController controller = new US03_AddCourseToProgrammeController(programmeService, courseService, studyPlanService, courseInStudyPlanService);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        Course course = courseFactory.createCourse(name, acronym);

        int semester = 2;
        int curricularYear = 2;
        int duration = 1;
        double quantEcts = 10.0;

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        MaxEcts maxQuantEcts = new MaxEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = studyPlanFactory.createStudyPlan(programmeID, date, durationInYears, maxQuantEcts);

        controller.addCourseToProgramme(semester, curricularYear, course, studyPlan, duration, quantEcts);

        // act
        boolean result = controller.addCourseToProgramme(semester, curricularYear, course, studyPlan, duration, quantEcts);

        // assert
        assertFalse(result);
    }
    }