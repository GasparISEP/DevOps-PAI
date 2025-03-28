package PAI.factory;

import PAI.VOs.CourseID;
import PAI.VOs.CurricularYear;
import PAI.VOs.Semester;
import PAI.VOs.StudyPlanID;
import PAI.domain.CourseInStudyPlan_2;

public interface ICourseInStudyPlanFactory_2 {

        CourseInStudyPlan_2 newCourseInStudyPlan_2(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID) throws Exception;
}
