package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.Course;
import PAI.domain.course.CourseFactoryImpl;
import PAI.domain.course.ICourseFactoryDDD;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanFactoryImpl;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.programme.Programme;
import PAI.domain.programme.ProgrammeFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanFactory;
import PAI.domain.studyPlan.StudyPlan;
import PAI.domain.studyPlan.StudyPlanFactoryImpl;
import PAI.repository.courseInStudyPlanRepo.CourseInStudyPlanDDDDDDRepositoryImpl;
import PAI.repository.courseInStudyPlanRepo.CourseInStudyPlanDDDListFactoryImpl;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDListFactory;
import PAI.repository.courseInStudyPlanRepo.ICourseInStudyPlanDDDRepository;
import PAI.repository.courseRepositoryDDD.CourseRepositoryDDDImpl;
import PAI.repository.courseRepositoryDDD.CourseRepositoryListFactoryImpl;
import PAI.repository.courseRepositoryDDD.ICourseRepositoryDDD;
import PAI.repository.courseRepositoryDDD.ICourseRepositoryListFactoryDDD;
import PAI.repository.programmeRepo.IProgrammeDDDRepository;
import PAI.repository.programmeRepo.IProgrammeDDDRepositoryListFactory;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryImpl;
import PAI.repository.programmeRepo.ProgrammeDDDRepositoryListFactoryImpl;
import PAI.repository.studyPlanRepo.IStudyPlanDDDListFactory;
import PAI.repository.studyPlanRepo.IStudyPlanDDDRepository;
import PAI.repository.studyPlanRepo.StudyPlanDDDListFactoryImpl;
import PAI.repository.studyPlanRepo.StudyPlanDDDRepositoryImpl;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class US03AddCourseToProgrammeControllerTest {

    private static final Log log = LogFactory.getLog(US03AddCourseToProgrammeControllerTest.class);
    private US03_AddCourseToProgrammeController us03AddCourseToProgrammeController;
    private IProgrammeDDDRepository iProgrammeDDDRepository;
    private ICourseRepositoryDDD iCourseRepositoryDDD;
    private IStudyPlanDDDRepository iStudyPlanDDDRepository;
    private ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository;

    @BeforeEach
    void setUp() throws Exception {
        iProgrammeDDDRepository = mock(IProgrammeDDDRepository.class);
        iCourseRepositoryDDD = mock(ICourseRepositoryDDD.class);
        iStudyPlanDDDRepository = mock(IStudyPlanDDDRepository.class);
        iCourseInStudyPlanDDDRepository = mock(ICourseInStudyPlanDDDRepository.class);
        us03AddCourseToProgrammeController = new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
    }

    @Test
    void shouldReturnFalseIfNotAddCourseToProgramme_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        Course course = mock(Course.class);
        StudyPlan studyPlan = mock(StudyPlan.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(course.identity()).thenReturn(courseID);
        when(studyPlan.identity()).thenReturn(studyPlanID);
        when(iCourseInStudyPlanDDDRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID)).thenReturn(false);

        // act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
        //assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        Course course = mock(Course.class);
        StudyPlan studyPlan = mock(StudyPlan.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(course.identity()).thenReturn(courseID);
        when(studyPlan.identity()).thenReturn(studyPlanID);
        when(iCourseInStudyPlanDDDRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID)).thenReturn(true);
        //act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
        //assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfPCourseRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository,null, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfPStudyPlanRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository,iCourseRepositoryDDD, null, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfPCourseInStudyPlanRepositoryIsNull_IsolatedTest() throws IllegalArgumentException {
        // arrange
        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository,iCourseRepositoryDDD, iStudyPlanDDDRepository, null);
        });
    }


    @Test
    void shouldReturnAllProgrammes_IsolatedTest() {
        // arrange
        List<Programme> programmeList = mock(List.class);

        when(iProgrammeDDDRepository.findAll()).thenReturn(programmeList);

        // act
        Iterable<Programme> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programmeList, result);
    }

    @Test
    void shouldReturnAllCourses_IsolatedTest() {
        // arrange
        List<Course> courseListDouble = mock(List.class);
        when(iCourseRepositoryDDD.findAll()).thenReturn(courseListDouble);
        // act
        Iterable<Course> result = us03AddCourseToProgrammeController.getAllCourses();
        // assert
        assertEquals(courseListDouble, result);
    }

    @Test
    void shouldReturnAllStudyPlansByProgrammeID_IsolatedTest() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        List<StudyPlan> studyPlanList = mock(List.class);
        when(iStudyPlanDDDRepository.getAllStudyPlansByProgrammeId(programmeID)).thenReturn(studyPlanList);
        // act
        Iterable<StudyPlan> result = us03AddCourseToProgrammeController.getAllStudyPlansByProgrammeId(programmeID);
        // assert
        assertEquals(studyPlanList, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCourseIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        StudyPlan studyPlan = mock(StudyPlan.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlan);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfStudyPlanIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        Course course = mock(Course.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, null);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCurricularYearIsNull_IsolatedTest() throws Exception {
        // arrange
        StudyPlan studyPlan = mock(StudyPlan.class);
        Semester semester = mock(Semester.class);
        Course course = mock(Course.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, null, course, studyPlan);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfSemesterIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlan studyPlan = mock(StudyPlan.class);
        Course course = mock(Course.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(null , curricularYear, course, studyPlan);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeIdIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            us03AddCourseToProgrammeController.getAllStudyPlansByProgrammeId(null);
        });
    }

    // Integration Tests

    @Test
    void shouldThrowExceptionIfCourseRepositoryNull() throws Exception {
        // arrange
        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, null, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryNull() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanRepositoryNull() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, null, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfCourseInStudyPlanRepositoryNull() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, null);
        });
    }

    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);
        //act
        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //assert
        assertNotNull(US03AddCourseToProgrammeController);
    }


    @Test
    void shouldAddCourseToProgramme_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        Course course = new Course(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
        // assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseInStudyPlanAlreadyExists_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        Course course = new Course(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, studyPlan);
        // assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldNotThrowExceptionIfCourseIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlan);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        Course course = new Course(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, course, null);
        });
    }

    @Test
    void shouldThrowExceptionIfCurricularYearIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        Course course = new Course(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, null, course, studyPlan);
        });
    }

    @Test
    void shouldThrowExceptionIfSemesterIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeFactoryImpl programmeDDDFactory = new ProgrammeFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanFactory iCourseInStudyPlanFactory = new CourseInStudyPlanFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanFactory iStudyPlanFactory = new StudyPlanFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        Course course = new Course(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlan studyPlan = new StudyPlan(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(null, curricularYear, course, studyPlan);
        });
    }
}
