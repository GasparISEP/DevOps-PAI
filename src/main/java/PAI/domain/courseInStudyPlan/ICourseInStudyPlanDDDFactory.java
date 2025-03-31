package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;

public interface ICourseInStudyPlanDDDFactory {
        CourseInStudyPlanDDD newCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) throws Exception;
}
