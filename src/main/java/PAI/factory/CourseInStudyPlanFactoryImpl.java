package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseInStudyPlan;
import PAI.domain.Programme;

public interface CourseInStudyPlanFactoryImpl {
    CourseInStudyPlan newCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception;
}
