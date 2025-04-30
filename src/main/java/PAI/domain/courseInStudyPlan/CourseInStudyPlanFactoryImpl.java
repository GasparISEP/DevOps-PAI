package PAI.domain.courseInStudyPlan;

import PAI.VOs.*;
import org.springframework.stereotype.Component;

@Component
public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                  DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        CourseInStudyPlanID courseInStudyPlanID = new CourseInStudyPlanID(courseID, studyPlanID);

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);
    }

    public CourseInStudyPlan newCourseInStudyPlanFromDataModel(CourseInStudyPlanID courseInStudyPlanID, Semester semester, CurricularYear curricularYear, CourseID courseID, StudyPlanID studyPlanID,
                                                               DurationCourseInCurricularYear durationOfCourse, CourseQuantityCreditsEcts quantityOfCreditsEcts) {

        return new CourseInStudyPlan(semester, curricularYear, courseID, studyPlanID, courseInStudyPlanID, durationOfCourse, quantityOfCreditsEcts);

    }
}