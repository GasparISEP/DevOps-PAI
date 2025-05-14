package PAI.domain.repositoryInterfaces.courseInStudyPlan;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

public interface ICourseInStudyPlanRepository extends IRepository<CourseInStudyPlanID, CourseInStudyPlan> {

    double getTotalCreditsEctsInStudyPlanSoFar (
            StudyPlanID studyPlanID, Semester semester, CurricularYear curricularYear, DurationCourseInCurricularYear duration);
}
