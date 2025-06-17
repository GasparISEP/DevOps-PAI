package PAI.domain.courseInStudyPlan;

import PAI.dto.courseInStudyPlan.CourseInStudyPlanCommand;
import PAI.VOs.*;

public interface ICourseInStudyPlanFactory {
    CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                           DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID);

    CourseInStudyPlan newCourseInStudyPlanFromDataModel(CourseInStudyPlanID courseInStudyPlanID, CourseInStudyPlanGeneratedID generatedID, Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                        DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts, ProgrammeID programmeID);

    CourseInStudyPlan newCourseInStudyPlan(CourseInStudyPlanCommand command) throws Exception;
}