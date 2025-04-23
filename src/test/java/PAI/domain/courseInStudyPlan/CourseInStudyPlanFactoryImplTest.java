package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class CourseInStudyPlanFactoryImplTest {

    @Test
    void shouldCreateFactoryConstrutor() throws Exception {

        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyplanID = mock(StudyPlanID.class);
        DurationCourseInCurricularYear durationOfCourse = mock(DurationCourseInCurricularYear.class);
        CourseQuantityCreditsEcts quantityOfCreditsEcts = mock(CourseQuantityCreditsEcts.class);

        //act
        ICourseInStudyPlanFactory courseInStudyPlanFactory_2 = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlan courseInStudyPlan_DDD = courseInStudyPlanFactory_2.newCourseInStudyPlan(semester, curricularYear, courseID, studyplanID, durationOfCourse, quantityOfCreditsEcts);

        //assert
        assertNotNull(courseInStudyPlan_DDD);
    }
}