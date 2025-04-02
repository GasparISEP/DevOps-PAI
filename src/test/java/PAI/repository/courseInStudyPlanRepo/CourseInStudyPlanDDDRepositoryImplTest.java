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

class CourseInStudyPlanDDDRepositoryTest {

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

        CourseInStudyPlanDDDRepository repository = new CourseInStudyPlanDDDRepository(factory, listFactory);

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

        CourseInStudyPlanDDDRepository repository = new CourseInStudyPlanDDDRepository(factory, listFactory);

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

        CourseInStudyPlanDDDRepository repository = new CourseInStudyPlanDDDRepository(factory, listFactory);

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

        CourseInStudyPlanDDDRepository repository = new CourseInStudyPlanDDDRepository(factory, listFactory);

        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Act
        CourseInStudyPlanID nonExistentID = new CourseInStudyPlanID(courseID, studyPlanID);
        Optional<CourseInStudyPlanDDD> foundCourseOpt = repository.findCourseInStudyPlanByID(nonExistentID);

        // Assert
        assertFalse(foundCourseOpt.isPresent());
    }
}