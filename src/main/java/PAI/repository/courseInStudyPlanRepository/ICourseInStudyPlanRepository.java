package PAI.repository.courseInStudyPlanRepository;

import PAI.VOs.*;
import PAI.ddd.IRepository;
import PAI.domain.courseInStudyPlan.CourseInStudyPlan;

import java.util.List;

public interface ICourseInStudyPlanRepository extends IRepository<CourseInStudyPlanID, CourseInStudyPlan> {

    double getTotalCreditsEctsInStudyPlanSoFar (
            StudyPlanID studyPlanID, Semester semester, CurricularYear curricularYear, DurationCourseInCurricularYear duration);
}
