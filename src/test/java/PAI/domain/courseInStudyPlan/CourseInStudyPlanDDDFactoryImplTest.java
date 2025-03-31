package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class CourseInStudyPlanDDDFactoryImplTest {

    @Test
    void shouldCreateFactoryConstrutor() throws Exception {

        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyplanID = mock(StudyPlanID.class);

        //act
        ICourseInStudyPlanDDDFactory courseInStudyPlanFactory_2 = new CourseInStudyPlanDDDDDDFactoryImpl();
        CourseInStudyPlanDDD courseInStudyPlan_DDD = courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyplanID);

        //assert
        assertNotNull(courseInStudyPlan_DDD);
    }
}