package PAI.service.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.exception.BusinessRuleViolationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CourseInStudyPlanServiceImplTest {

    @Mock
    private ICourseInStudyPlanRepository repository;

    @Mock
    private ICourseInStudyPlanFactory factory;

    @InjectMocks
    private CourseInStudyPlanServiceImpl service;

    private Semester semester;
    private CurricularYear curricularYear;
    private CourseID courseId;
    private StudyPlanID studyPlanId;
    private CourseInStudyPlan candidate;
    private CourseInStudyPlanID candidateId;
    private DurationCourseInCurricularYear durationOfCourse;
    private CourseQuantityCreditsEcts quantityOfCreditsEcts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        semester = mock(Semester.class);
        curricularYear = mock(CurricularYear.class);
        courseId = mock(CourseID.class);
        studyPlanId = mock(StudyPlanID.class);

        candidate = mock(CourseInStudyPlan.class);
        candidateId = mock(CourseInStudyPlanID.class);

        durationOfCourse = mock(DurationCourseInCurricularYear.class);
        quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);


        when(candidate.identity()).thenReturn(candidateId);
        when(factory.newCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);
    }

    @Test
    void createCourseInStudyPlan_SuccessWhenNotExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(false);

        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts);

        assertTrue(result);
        verify(repository).save(candidate);
    }

    @Test
    void createCourseInStudyPlan_FailsWhenAlreadyExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(true);

        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts);

        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void getAllCoursesInStudyPlan_ReturnsListFromRepository() throws Exception {
        CourseInStudyPlan c1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan c2 = mock(CourseInStudyPlan.class);
        when(repository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CourseInStudyPlan> all = service.getAllCoursesInStudyPlan();

        assertEquals(2, all.size());
        assertTrue(all.contains(c1));
        assertTrue(all.contains(c2));
    }

    @Test
    void getCoursesByStudyPlanId_FiltersCorrectly() throws Exception {
        CourseInStudyPlan c1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan c2 = mock(CourseInStudyPlan.class);
        StudyPlanID otherId = mock(StudyPlanID.class);

        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);

        when(c1.identity()).thenReturn(id1);
        when(c2.identity()).thenReturn(id2);
        when(id1.getStudyPlanID()).thenReturn(studyPlanId);
        when(id2.getStudyPlanID()).thenReturn(otherId);

        when(repository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<CourseInStudyPlan> filtered = service.getCoursesByStudyPlanId(studyPlanId);

        assertEquals(1, filtered.size());
        assertSame(c1, filtered.get(0));
    }

    @Test
    void findById_ReturnsOptionalFromRepository() {
        when(repository.ofIdentity(candidateId)).thenReturn(Optional.of(candidate));

        Optional<CourseInStudyPlan> opt = service.findById(candidateId);

        assertTrue(opt.isPresent());
        assertSame(candidate, opt.get());
    }

    @Test
    void findById_ReturnsEmptyWhenNotFound() {
        when(repository.ofIdentity(candidateId)).thenReturn(Optional.empty());

        Optional<CourseInStudyPlan> opt = service.findById(candidateId);

        assertFalse(opt.isPresent());
    }

    @Test
    void shouldNotCreateCourseInStudyPlanWhenTotalCreditsExceedsLimit() throws Exception {
        //arrange
        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanId, semester, curricularYear, durationOfCourse)).thenReturn(31.0);

        //act
        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts);

        //assert
        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void should_Not_CreateCourseInStudyPlan_WhenTotalCreditsExceedsLimits() throws Exception {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023"); // Corrected date format
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(5.0);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        CourseID courseID = new CourseID(new Acronym("CS101"), new Name("Computer Science"));
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(
                        new NameWithNumbersAndSpecialChars("Computer Science Programme"),
                        new Acronym("CS")
                ),
                new PAI.VOs.Date("01-01-2023") // Corrected date format
        );
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(5.0);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(29.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }

    @Test
    void should_CreateCourseInStudyPlan_WhenTotalCreditsDoesNotExceedLimits() throws Exception {
        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023"); // Corrected date format
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(5.0);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        CourseID courseID = new CourseID(new Acronym("CS101"), new Name("Computer Science"));
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(
                        new NameWithNumbersAndSpecialChars("Computer Science Programme"),
                        new Acronym("CS")
                ),
                new PAI.VOs.Date("01-01-2023")
        );
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(5.0);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        // act
        CourseInStudyPlan result = service.createCourseInStudyPlan(command);

        // assert
        assertNotNull(result);
        assertEquals(candidate, result);
    }

    @Test
    void should_CreateCourseInStudyPlan_WhenTotalCreditsDoesNotReachLimit() throws Exception {
        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023"); // Corrected date format
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        CourseID courseID = new CourseID(new Acronym("CS101"), new Name("Computer Science"));
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(
                        new NameWithNumbersAndSpecialChars("Computer Science Programme"),
                        new Acronym("CS")
                ),
                new PAI.VOs.Date("01-01-2023")
        );
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(3.0);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        // act
        CourseInStudyPlan result = service.createCourseInStudyPlan(command);

        // assert
        assertNotNull(result);
        assertEquals(candidate, result);
    }

    @Test
    void should_CreateCourseInStudyPlan_ifCourseDoesntExist() throws Exception {
        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023"); // Corrected date format
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        Semester semester = new Semester(1);
        CurricularYear curricularYear = new CurricularYear(1);
        CourseID courseID = new CourseID(new Acronym("CS101"), new Name("Computer Science"));
        StudyPlanID studyPlanID = new StudyPlanID(
                new ProgrammeID(
                        new NameWithNumbersAndSpecialChars("Computer Science Programme"),
                        new Acronym("CS")
                ),
                new PAI.VOs.Date("01-01-2023")
        );
        DurationCourseInCurricularYear durationOfCourse = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = new CourseQuantityCreditsEcts(3.0);

        when(repository.getTotalCreditsEctsInStudyPlanSoFar(studyPlanID, semester, curricularYear, durationOfCourse))
                .thenReturn(25.0);

        when(factory.newCourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, durationOfCourse, quantityOfCreditsEcts))
                .thenReturn(candidate);

        // act
        CourseInStudyPlan result = service.createCourseInStudyPlan(command);

        // assert
        assertNotNull(result);
        assertEquals(candidate, result);
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifSemesterIsNotValid() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(0);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023");
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifCurricularYearIsInvalid() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(0);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023");
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }
    @Test
    void should_NotCreateCourseInStudyPlan_ifCourseIsNull() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn(null);
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023");
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifStudyPlanIDIsNull() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn(null);
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(3.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifDurationOfCourseIsInvalid() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023");
        when(command.duration()).thenReturn(0);
        when(command.credits()).thenReturn(3.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }

    @Test
    void should_NotCreateCourseInStudyPlan_ifQuantityOfCreditsECTSIsInvalid() throws IllegalArgumentException {

        // arrange
        CourseInStudyPlanCommand command = mock(CourseInStudyPlanCommand.class);
        when(command.semester()).thenReturn(1);
        when(command.curricularYear()).thenReturn(1);
        when(command.courseAcronym()).thenReturn("CS101");
        when(command.courseName()).thenReturn("Computer Science");
        when(command.programmeAcronym()).thenReturn("CS");
        when(command.programmeName()).thenReturn("Computer Science Programme");
        when(command.studyPlanDate()).thenReturn("01-01-2023");
        when(command.duration()).thenReturn(1);
        when(command.credits()).thenReturn(0.0);

        // act + assert
        assertThrows(IllegalArgumentException.class, () -> {
            service.createCourseInStudyPlan(command);
        });
    }
}