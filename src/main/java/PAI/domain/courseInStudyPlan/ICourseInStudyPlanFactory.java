package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;

public interface ICourseInStudyPlanFactory {
        CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts);
}