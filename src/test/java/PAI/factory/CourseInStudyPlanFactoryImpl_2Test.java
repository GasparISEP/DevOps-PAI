package PAI.factory;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;
import PAI.domain.CourseInStudyPlan_2;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

class CourseInStudyPlanFactoryImpl_2Test {

    @Test
    void shouldCreateFactoryConstrutor() throws Exception {

        //arrange
        Semester semester = mock(Semester.class);
        CurricularYear curricularYear = mock(CurricularYear.class);
        CourseID courseID = mock(CourseID.class);
        StudyPlanID studyplanID = mock(StudyPlanID.class);

        //act
        ICourseInStudyPlanFactory_2 courseInStudyPlanFactory_2 = new CourseInStudyPlanFactoryImpl_2();
        CourseInStudyPlan_2 courseInStudyPlan_2 = courseInStudyPlanFactory_2.newCourseInStudyPlan_2(semester, curricularYear, courseID, studyplanID);

        //assert
        assertNotNull(courseInStudyPlan_2);
    }
}