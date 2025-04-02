package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;

public class CourseInStudyPlanDDDDDDFactoryImpl implements ICourseInStudyPlanDDDFactory {
        public CourseInStudyPlanDDD newCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) {
            return new CourseInStudyPlanDDD(semester, curricularYear, courseID, studyPlanID);
        }
}