package PAI.controller;

import PAI.VOs.*;
import PAI.domain.course.CourseDDD;
import PAI.domain.course.CourseFactoryDDDImpl;
import PAI.domain.course.ICourseFactoryDDD;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDDDDDFactoryImpl;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanDDDFactory;
import PAI.domain.programme.ProgrammeDDD;
import PAI.domain.programme.ProgrammeDDDFactoryImpl;
import PAI.domain.studyPlan.IStudyPlanDDDFactory;
import PAI.domain.studyPlan.StudyPlanDDD;
import PAI.domain.studyPlan.StudyPlanDDDFactoryImpl;
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
        CourseDDD courseDDD = mock(CourseDDD.class);
        StudyPlanDDD studyPlanDDD = mock(StudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(courseDDD.identity()).thenReturn(courseID);
        when(studyPlanDDD.identity()).thenReturn(studyPlanID);
        when(iCourseInStudyPlanDDDRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID)).thenReturn(false);

        // act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, studyPlanDDD);
        //assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldAddCourseToProgramme() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        StudyPlanDDD studyPlanDDD = mock(StudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(courseDDD.identity()).thenReturn(courseID);
        when(studyPlanDDD.identity()).thenReturn(studyPlanID);
        when(iCourseInStudyPlanDDDRepository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID)).thenReturn(true);
        //act
        boolean addCourseToProgramme = us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, studyPlanDDD);
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
        List<ProgrammeDDD> programmeList = mock(List.class);

        when(iProgrammeDDDRepository.findAll()).thenReturn(programmeList);

        // act
        Iterable<ProgrammeDDD> result = us03AddCourseToProgrammeController.getAllProgrammes();

        // assert
        assertEquals(programmeList, result);
    }

    @Test
    void shouldReturnAllCourses_IsolatedTest() {
        // arrange
        List<CourseDDD> courseListDouble = mock(List.class);
        when(iCourseRepositoryDDD.findAll()).thenReturn(courseListDouble);
        // act
        Iterable<CourseDDD> result = us03AddCourseToProgrammeController.getAllCourses();
        // assert
        assertEquals(courseListDouble, result);
    }

    @Test
    void shouldReturnAllStudyPlansByProgrammeID_IsolatedTest() {
        // arrange
        ProgrammeID programmeID = mock(ProgrammeID.class);
        List<StudyPlanDDD> studyPlanDDDList = mock(List.class);
        when(iStudyPlanDDDRepository.getAllStudyPlansByProgrammeId(programmeID)).thenReturn(studyPlanDDDList);
        // act
        Iterable<StudyPlanDDD> result = us03AddCourseToProgrammeController.getAllStudyPlansByProgrammeId(programmeID);
        // assert
        assertEquals(studyPlanDDDList, result);
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCourseIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        StudyPlanDDD studyPlanDDD = mock(StudyPlanDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlanDDD);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfStudyPlanIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        Semester semester = mock(Semester.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, null);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfCurricularYearIsNull_IsolatedTest() throws Exception {
        // arrange
        StudyPlanDDD studyPlanDDD = mock(StudyPlanDDD.class);
        Semester semester = mock(Semester.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(semester, null, courseDDD, studyPlanDDD);
        });
    }

    @Test
    void shouldThrowIllegalArgumentExceptionIfSemesterIsNull_IsolatedTest() throws Exception {
        // arrange
        CurricularYear curricularYear = mock(CurricularYear.class);
        StudyPlanDDD studyPlanDDD = mock(StudyPlanDDD.class);
        CourseDDD courseDDD = mock(CourseDDD.class);
        // act & assert
        assertThrows(IllegalArgumentException.class, () -> {us03AddCourseToProgrammeController.addCourseToProgramme(null , curricularYear, courseDDD, studyPlanDDD);
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
        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, null, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfProgrammeRepositoryNull() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(null, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanRepositoryNull() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
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
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, null);
        });
    }

    @Test
    void shouldCreateAddCourseToProgrammeController() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);
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
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlanDDD studyPlanDDD = new StudyPlanDDD(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, studyPlanDDD);
        // assert
        assertTrue(addCourseToProgramme);
    }

    @Test
    void shouldNotAddCourseToProgrammeIfCourseInStudyPlanAlreadyExists_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlanDDD studyPlanDDD = new StudyPlanDDD(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, studyPlanDDD);
        //act
        boolean addCourseToProgramme = US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, studyPlanDDD);
        // assert
        assertFalse(addCourseToProgramme);
    }

    @Test
    void shouldNotThrowExceptionIfCourseIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlanDDD studyPlanDDD = new StudyPlanDDD(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, null, studyPlanDDD);
        });
    }

    @Test
    void shouldThrowExceptionIfStudyPlanIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);
        CurricularYear curricularYear = new CurricularYear(2, 3);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, curricularYear, courseDDD, null);
        });
    }

    @Test
    void shouldThrowExceptionIfCurricularYearIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        Semester semester = new Semester(2);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlanDDD studyPlanDDD = new StudyPlanDDD(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(semester, null, courseDDD, studyPlanDDD);
        });
    }

    @Test
    void shouldThrowExceptionIfSemesterIsNull_IntegrationTest() throws Exception {
        // arrange
        ICourseRepositoryListFactoryDDD iCourseRepositoryListFactoryDDD = new CourseRepositoryListFactoryImpl();
        ICourseFactoryDDD iCourseFactoryDDD = new CourseFactoryDDDImpl();
        ICourseRepositoryDDD iCourseRepositoryDDD = new CourseRepositoryDDDImpl(iCourseFactoryDDD, iCourseRepositoryListFactoryDDD);

        ProgrammeDDDFactoryImpl programmeDDDFactory = new ProgrammeDDDFactoryImpl();
        IProgrammeDDDRepositoryListFactory iProgrammeDDDRepositoryListFactory = new ProgrammeDDDRepositoryListFactoryImpl();
        IProgrammeDDDRepository iProgrammeDDDRepository = new ProgrammeDDDRepositoryImpl(programmeDDDFactory, iProgrammeDDDRepositoryListFactory);

        ICourseInStudyPlanDDDFactory iCourseInStudyPlanFactory = new CourseInStudyPlanDDDDDDFactoryImpl();
        ICourseInStudyPlanDDDListFactory iCourseInStudyPlanDDDListFactory = new CourseInStudyPlanDDDListFactoryImpl();
        ICourseInStudyPlanDDDRepository iCourseInStudyPlanDDDRepository = new CourseInStudyPlanDDDDDDRepositoryImpl(iCourseInStudyPlanFactory, iCourseInStudyPlanDDDListFactory);

        IStudyPlanDDDFactory iStudyPlanDDDFactory = new StudyPlanDDDFactoryImpl();
        IStudyPlanDDDListFactory iStudyPlanDDDListFactory = new StudyPlanDDDListFactoryImpl();
        IStudyPlanDDDRepository iStudyPlanDDDRepository = new StudyPlanDDDRepositoryImpl(iStudyPlanDDDFactory, iStudyPlanDDDListFactory);

        Name name = new Name("Licenciatura Engenharia Informática");
        Acronym acronym = new Acronym("LEI");
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(5);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseDDD courseDDD = new CourseDDD(name, acronym, courseQuantityCreditsEcts, durationCourseInCurricularYear);

        CurricularYear curricularYear = new CurricularYear(2, 3);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars = new NameWithNumbersAndSpecialChars("LEI");
        Acronym acronymProgramme = new Acronym("LEI");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars, acronymProgramme);
        QuantEcts quantEcts = new QuantEcts(30);
        Date date = new Date("10-10-2022");
        DurationInYears durationInYears = new DurationInYears(6);
        StudyPlanDDD studyPlanDDD = new StudyPlanDDD(programmeID, date, durationInYears, quantEcts);

        US03_AddCourseToProgrammeController US03AddCourseToProgrammeController =
                new US03_AddCourseToProgrammeController(iProgrammeDDDRepository, iCourseRepositoryDDD, iStudyPlanDDDRepository, iCourseInStudyPlanDDDRepository);
        //act & assert
        assertThrows(IllegalArgumentException.class, () -> {
            US03AddCourseToProgrammeController.addCourseToProgramme(null, curricularYear, courseDDD, studyPlanDDD);
        });
    }
}
