package PAI.repository;

import PAI.VOs.*;
import PAI.domain.CourseInStudyPlan_2;
import PAI.factory.ICourseInStudyPlanFactory_2;
import PAI.factory.ICourseInStudyPlanListFactory_2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CourseInStudyPlanRepository_2Test {

    @Test
    void testCreateCourseInStudyPlanAddsNewCourse() throws Exception {
        // Arrange
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        // Simular o comportamento da fábrica para criar uma nova instância
        // A igualdade de CourseInStudyPlan_2 depende do CourseID, portanto, chamadas com os mesmos parâmetros produzirão objetos iguais.
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

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
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID1 = mock(CourseID.class);
        CourseID courseID2 = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID));
        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act
        boolean created1 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID1, studyPlanID);
        boolean created2 = repository.createCourseInStudyPlan_2(semester, curricularYear, courseID2, studyPlanID);
        List<CourseInStudyPlan_2> allCourses = repository.getAllCourseInStudyPlanList_2();

        // Assert
        assertTrue(created1);
        assertTrue(created2);
        assertEquals(2, allCourses.size());
    }

    @Test
    void testFindByCourseInStudyPlanIDFound() throws Exception {
        // Arrange
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyPlanID = mock(StudyPlanID.class);

        when(factory.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID))
                .thenAnswer(invocation -> new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID));

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act
        repository.createCourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);

        CourseInStudyPlan_2 createdCourse = repository.getAllCourseInStudyPlanList_2().get(0);
        CourseInStudyPlanID courseInStudyPlanID = createdCourse.getCourseInStudyPlanID();

        // Act
        Optional<CourseInStudyPlan_2> foundCourseOpt = repository.findByCourseInStudyPlanID(courseInStudyPlanID);

        // Assert
        assertTrue(foundCourseOpt.isPresent());
        assertEquals(createdCourse, foundCourseOpt.get());
    }

    @Test
    void testFindByCourseInStudyPlanIDNotFound() throws Exception {
        // Arrange
        ICourseInStudyPlanFactory_2 factory = mock(ICourseInStudyPlanFactory_2.class);
        ICourseInStudyPlanListFactory_2 listFactory = mock(ICourseInStudyPlanListFactory_2.class);
        List<CourseInStudyPlan_2> courseList = new ArrayList<>();
        when(listFactory.newArrayList()).thenReturn(courseList);

        CourseInStudyPlanRepository_2 repository = new CourseInStudyPlanRepository_2(factory, listFactory);

        // Act
        CourseInStudyPlanID nonExistentID = new CourseInStudyPlanID();
        Optional<CourseInStudyPlan_2> foundCourseOpt = repository.findByCourseInStudyPlanID(nonExistentID);

        // Assert
        assertFalse(foundCourseOpt.isPresent());
    }
}