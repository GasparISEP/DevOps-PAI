package PAI.domain.courseInStudyPlan;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;

public interface ICourseInStudyPlanFactory {
        CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID);
}
