package PAI.service.courseInStudyPlan;

import PAI.VOs.*;
import PAI.assembler.courseInStudyPlan.ICourseInStudyPlanServiceAssembler;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanFactory;
import PAI.domain.repositoryInterfaces.courseInStudyPlan.ICourseInStudyPlanRepository;
import PAI.dto.courseInStudyPlan.CourseInStudyPlanServiceDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
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

    @Mock
    private ICourseInStudyPlanServiceAssembler assembler;

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
    private ProgrammeID programmeID;

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
        programmeID = mock(ProgrammeID.class);


        when(candidate.identity()).thenReturn(candidateId);
        when(factory.newCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts, programmeID))
                .thenReturn(candidate);
    }

    @Test
    void createCourseInStudyPlan_SuccessWhenNotExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(false);

        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts, programmeID);

        assertTrue(result);
        verify(repository).save(candidate);
    }

    @Test
    void createCourseInStudyPlan_FailsWhenAlreadyExists() throws Exception {
        when(repository.containsOfIdentity(candidateId)).thenReturn(true);

        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts, programmeID);

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
        boolean result = service.createCourseInStudyPlan(semester, curricularYear, courseId, studyPlanId, durationOfCourse, quantityOfCreditsEcts, programmeID);

        //assert
        assertFalse(result);
        verify(repository, never()).save(any());
    }

    @Test
    void getCourseSummariesByStudyPlanID_returnsDTOList_whenMatchingCoursesExist() {
        // Arrange
        StudyPlanID spId = mock(StudyPlanID.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course2 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan course3 = mock(CourseInStudyPlan.class);

        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id2 = mock(CourseInStudyPlanID.class);
        CourseInStudyPlanID id3 = mock(CourseInStudyPlanID.class);

        when(course1.identity()).thenReturn(id1);
        when(course2.identity()).thenReturn(id2);
        when(course3.identity()).thenReturn(id3);

        when(id1.getStudyPlanID()).thenReturn(spId);
        when(id2.getStudyPlanID()).thenReturn(spId);
        when(id3.getStudyPlanID()).thenReturn(mock(StudyPlanID.class)); // diferente

        List<CourseInStudyPlan> courses = List.of(course1, course2, course3);
        when(repository.findAll()).thenReturn(courses);

        CourseInStudyPlanServiceDTO dto1 = mock(CourseInStudyPlanServiceDTO.class);
        CourseInStudyPlanServiceDTO dto2 = mock(CourseInStudyPlanServiceDTO.class);

        when(assembler.toServiceDTO(course1)).thenReturn(dto1);
        when(assembler.toServiceDTO(course2)).thenReturn(dto2);

        // Act
        List<CourseInStudyPlanServiceDTO> result = service.getCourseSummariesByStudyPlanID(spId);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(dto1));
        assertTrue(result.contains(dto2));
    }


    @Test
    void getCourseSummariesByStudyPlanID_returnsEmptyList_whenNoMatchingCourses() {
        // Arrange
        StudyPlanID spId = mock(StudyPlanID.class);

        CourseInStudyPlan course1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlanID id1 = mock(CourseInStudyPlanID.class);
        when(course1.identity()).thenReturn(id1);
        when(id1.getStudyPlanID()).thenReturn(mock(StudyPlanID.class)); // diferente do spId

        when(repository.findAll()).thenReturn(List.of(course1));

        // Act
        List<CourseInStudyPlanServiceDTO> result = service.getCourseSummariesByStudyPlanID(spId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getCourseSummariesByStudyPlanID_returnsEmptyList_whenRepositoryIsEmpty() {
        // Arrange
        StudyPlanID spId = mock(StudyPlanID.class);
        when(repository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<CourseInStudyPlanServiceDTO> result = service.getCourseSummariesByStudyPlanID(spId);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}