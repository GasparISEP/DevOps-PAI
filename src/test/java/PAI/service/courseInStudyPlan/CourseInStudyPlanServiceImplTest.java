package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.repository.courseInStudyPlanRepository.ICourseInStudyPlanRepository;
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
}
