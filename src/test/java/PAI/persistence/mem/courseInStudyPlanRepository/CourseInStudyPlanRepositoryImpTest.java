package PAI.persistence.mem.courseInStudyPlanRepository;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;
import PAI.persistence.mem.courseInStudyPlan.CourseInStudyPlanRepositoryImpl;
import PAI.persistence.mem.courseInStudyPlan.ICourseInStudyPlanListFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
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

    @Test
    void testOfIdentityShouldIterateOverMultipleElements() throws Exception {
        // arrange
        Acronym acronym1 = new Acronym("CCC");
        Name names1 = new Name("Course");
        CourseID courseID1 = new CourseID(acronym1, names1);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars1 = new NameWithNumbersAndSpecialChars("Programme");
        Acronym programmeAcronym1 = new Acronym("PR");
        ProgrammeID programmeID = new ProgrammeID(nameWithNumbersAndSpecialChars1, programmeAcronym1);
        LocalDate localDate1 = LocalDate.of(2020, 1, 1);
        Date implementationDate1 = new Date(localDate1);

        StudyPlanID studyPlanID1 = new StudyPlanID(programmeID, implementationDate1);
        CourseInStudyPlanID courseInStudyPlanIDPresent = new CourseInStudyPlanID(courseID1, studyPlanID1);

        Semester semester1 = new Semester(1);
        CurricularYear curricularYear1 = new CurricularYear(3);
        DurationCourseInCurricularYear durationCourseInCurricularYear = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts = new CourseQuantityCreditsEcts(10);

        CourseInStudyPlan courseInStudyPlan1 = new CourseInStudyPlan(semester1, curricularYear1, courseID1, studyPlanID1, courseInStudyPlanIDPresent, durationCourseInCurricularYear, courseQuantityCreditsEcts);

        Acronym acronym2 = new Acronym("CCS");
        Name names2 = new Name("Courses");
        CourseID courseID2 = new CourseID(acronym2, names2);

        NameWithNumbersAndSpecialChars nameWithNumbersAndSpecialChars2 = new NameWithNumbersAndSpecialChars("Programm");
        Acronym programmeAcronym2 = new Acronym("PGR");
        ProgrammeID programmeID2 = new ProgrammeID(nameWithNumbersAndSpecialChars2, programmeAcronym2);
        LocalDate localDate2 = LocalDate.of(2024, 1, 1);
        Date implementationDate2 = new Date(localDate2);

        StudyPlanID studyPlanID2 = new StudyPlanID(programmeID2, implementationDate2);
        CourseInStudyPlanID courseInStudyPlanIDNotPresent = new CourseInStudyPlanID(courseID2, studyPlanID2);

        Semester semester2 = new Semester(1);
        CurricularYear curricularYear2 = new CurricularYear(3);
        DurationCourseInCurricularYear durationCourseInCurricularYear2 = new DurationCourseInCurricularYear(1);
        CourseQuantityCreditsEcts courseQuantityCreditsEcts2 = new CourseQuantityCreditsEcts(10);

        CourseInStudyPlan courseInStudyPlan2 = new CourseInStudyPlan(semester2, curricularYear2, courseID2, studyPlanID2, courseInStudyPlanIDNotPresent, durationCourseInCurricularYear2, courseQuantityCreditsEcts2);

        repository.save(courseInStudyPlan1);

        Optional<CourseInStudyPlan> result = repository.ofIdentity(courseInStudyPlanIDNotPresent);

        assertFalse(result.isPresent());

    }
}
