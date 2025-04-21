package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;
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

        //act
        ICourseInStudyPlanFactory courseInStudyPlanFactory_2 = new CourseInStudyPlanFactoryImpl();
        CourseInStudyPlan courseInStudyPlan_DDD = courseInStudyPlanFactory_2.newCourseInStudyPlan(semester, curricularYear, courseID, studyplanID);

        //assert
        assertNotNull(courseInStudyPlan_DDD);
    }
}