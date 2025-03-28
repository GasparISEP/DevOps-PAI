package PAI.factory;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;
import PAI.domain.CourseInStudyPlan_2;

public class CourseInStudyPlanFactoryImpl_2 implements ICourseInStudyPlanFactory_2 {

        public CourseInStudyPlan_2 newCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) throws Exception {

            return new CourseInStudyPlan_2(semester, curricularYear, courseID, studyPlanID);
        }
}