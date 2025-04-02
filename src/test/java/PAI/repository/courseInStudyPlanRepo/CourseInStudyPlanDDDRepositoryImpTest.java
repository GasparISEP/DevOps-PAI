package PAI.repository.courseInStudyPlanRepo;

import PAI.VOs.*;
import PAI.domain.courseInStudyPlan.CourseInStudyPlanDDD;
import PAI.domain.courseInStudyPlan.ICourseInStudyPlanDDDFactory;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanDDDRepositoryImpTest {

    @Test
    void testCreateCourseInStudyPlanAddsNewCourse() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Simular o comportamento da fábrica para criar uma nova instância
        // A igualdade de CourseInStudyPlan_2 depende do CourseID, portanto, chamadas com os mesmos parâmetros produzirão objetos iguais.
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        boolean createdFirst = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        boolean createdSecond = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        // Assert
        assertTrue(createdFirst);
        assertFalse(createdSecond);
        assertEquals(1, repository.getAllCourseInStudyPlanList_2().size());
    }

    @Test
    public void testGetAllCourseInStudyPlanListReturnsAllCourses() throws Exception {
        // Arrange: criar mocks para as dependências
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID1, studyPlanID));
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID2, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        boolean created1 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        boolean created2 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);
        List<CourseInStudyPlanDDD> allCourses = repository.getAllCourseInStudyPlanList_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allCourses.size());
    }

    @Test
    void testFindCourseInStudyPlanByIDFound() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        // Act
        repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        CourseInStudyPlanDDD createdCourse = repository.getAllCourseInStudyPlanList_2().get(0);
        CourseInStudyPlanID courseInStudyPlanID = createdCourse.getCourseInStudyPlanID();

        // Act
        Optional<CourseInStudyPlanDDD> foundCourseOpt = repository.findCourseInStudyPlanByID(courseInStudyPlanID);

        // Assert
        assertTrue(foundCourseOpt.isPresent());
        assertEquals(createdCourse, foundCourseOpt.get());
    }

    @Test
    void testFindCourseInStudyPlanByIDNotFound() throws Exception {
        // Arrange
        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Act
        CourseInStudyPlanID nonExistentID = new CourseInStudyPlanID(courseID, studyPlanID);
        Optional<CourseInStudyPlanDDD> foundCourseOpt = repository.findCourseInStudyPlanByID(nonExistentID);

        // Assert
        assertFalse(foundCourseOpt.isPresent());
    }

    @Test
    void testSaveAddsCourseInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);
        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        when(courseInStudyPlanDDD.identity()).thenReturn(courseInStudyPlanID);

        CourseInStudyPlanDDD saved = repository.save(courseInStudyPlanDDD);

        //act + assert
        assertEquals(courseInStudyPlanDDD, saved);
        assertTrue(repository.containsOfIdentity(courseInStudyPlanID));
    }

    @Test
    void testFindAllReturnsAllCoursesInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD1 = mock(CourseInStudyPlanDDD.class);
        CourseInStudyPlanDDD courseInStudyPlanDDD2 = mock(CourseInStudyPlanDDD.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);

        repository.save(courseInStudyPlanDDD1);
        repository.save(courseInStudyPlanDDD2);

        //act
        List<CourseInStudyPlanDDD> all = (List<CourseInStudyPlanDDD>) repository.findAll();

        //assert
        assertEquals(2, all.size());
        assertTrue(all.contains(courseInStudyPlanDDD1) && all.contains(courseInStudyPlanDDD2));
    }

    @Test
    void testOfIdentityReturnsCorrectCourseInStudyPlan() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        when(courseInStudyPlanDDD.identity()).thenReturn(id);

        repository.save(courseInStudyPlanDDD);

        //act
        Optional<CourseInStudyPlanDDD> found = repository.ofIdentity(id);

        //assert
        assertTrue(found.isPresent());
        assertEquals(courseInStudyPlanDDD, found.get());
    }

    @Test
    void testOfIdentityReturnsEmptyWhenNotFound() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act
        Optional<CourseInStudyPlanDDD> found = repository.ofIdentity(id);

        //assert
        assertFalse(found.isPresent());
    }

    @Test
    void testContainsOfIdentityReturnsTrueWhenExists() {
        //arrange
        CourseInStudyPlanDDD courseInStudyPlanDDD = mock(CourseInStudyPlanDDD.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);
        when(courseInStudyPlanDDD.identity()).thenReturn(id);

        repository.save(courseInStudyPlanDDD);

        //act + assert
        assertTrue(repository.containsOfIdentity(id));
    }

    @Test
    void testContainsOfIdentityReturnsFalseWhenNotExists() {
        //arrange
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        ICourseInStudyPlanDDDFactory factory = mock(ICourseInStudyPlanDDDFactory.class);
        ICourseInStudyPlanDDDListFactory listFactory = mock(ICourseInStudyPlanDDDListFactory.class);
        List<CourseInStudyPlanDDD> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanDDDDDDRepositoryImpl repository = new CourseInStudyPlanDDDDDDRepositoryImpl(factory, listFactory);
        CourseInStudyPlanID id = new CourseInStudyPlanID(courseID, studyPlanID);

        //act + assert
        assertFalse(repository.containsOfIdentity(id));
    }
}
