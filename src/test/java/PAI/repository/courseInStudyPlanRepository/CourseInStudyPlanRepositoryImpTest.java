package PAI.repository.courseInStudyPlanRepository;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRepositoryImpTest {

    private ICourseInStudyPlanListFactory listFactory;
    private List<CourseInStudyPlan> courseList;
    private CourseInStudyPlanRepositoryImpl repository;

    @BeforeEach
    void setUp() {
        listFactory = mock(ICourseInStudyPlanListFactory.class);
        courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);
        repository = new CourseInStudyPlanRepositoryImpl(listFactory);
    }
    @Test
    void testSaveAddsEntityToListAndReturnsIt() {
        // arrange
        CourseInStudyPlan cip = mock(CourseInStudyPlan.class);

        // act
        CourseInStudyPlan saved = repository.save(cip);

        // assert
        assertSame(cip, saved);
        assertTrue(courseList.contains(cip));
    }

    @Test
    void testFindAllReturnsAllSavedEntities() {
        // arrange
        CourseInStudyPlan cip1 = mock(CourseInStudyPlan.class);
        CourseInStudyPlan cip2 = mock(CourseInStudyPlan.class);

        repository.save(cip1);
        repository.save(cip2);

        // act
        Iterable<CourseInStudyPlan> all = repository.findAll();

        // assert
        List<CourseInStudyPlan> asList = new ArrayList<>();
        all.forEach(asList::add);
        assertEquals(2, asList.size());
        assertTrue(asList.contains(cip1));
        assertTrue(asList.contains(cip2));
    }

    @Test
    void testGetAllCourseInStudyPlanListReturnsBackingListInstance() {
        // act
        List<CourseInStudyPlan> listFromRepo = repository.getAllCourseInStudyPlanList();

        // assert
        // deve ser exatamente a mesma instância que a factory devolveu
        assertSame(courseList, listFromRepo);
    }

    @Test
    void testOfIdentityReturnsPresentWhenFound() {
        // arrange
        CourseInStudyPlan cip = mock(CourseInStudyPlan.class);
        CourseID courseId = mock(CourseID.class);
        StudyPlanID studyPlanId = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseId, studyPlanId);

        when(cip.identity()).thenReturn(id);
        repository.save(cip);

        // act
        Optional<CourseInStudyPlan> found = repository.ofIdentity(id);

        // assert
        assertTrue(found.isPresent());
        assertSame(cip, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {
        // arrange
        CourseID courseId = mock(CourseID.class);
        StudyPlanID studyPlanId = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseId, studyPlanId);

        // act
        Optional<CourseInStudyPlan> found = repository.ofIdentity(id);

        // assert
        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityTrueWhenExists() {
        // arrange
        CourseInStudyPlan cip = mock(CourseInStudyPlan.class);
        CourseID courseId = mock(CourseID.class);
        StudyPlanID studyPlanId = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseId, studyPlanId);

        when(cip.identity()).thenReturn(id);
        repository.save(cip);

        // act + assert
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityFalseWhenNotExists() {
        // arrange
        CourseID courseId = mock(CourseID.class);
        StudyPlanID studyPlanId = mock(StudyPlanID.class);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseId, studyPlanId);

        // act + assert
        assertFalse(repository.containsOfIdentity(id));
    }
}
