package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;

public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {
        public CourseInStudyPlan newCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) {
            return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID);
        }
}