package PAI.factory;

import PAI.domain.Course;
import PAI.domain.CourseInStudyPlan;
import PAI.domain.Programme;

public class CourseInStudyPlanFactoryImpl implements ICourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {
        return new CourseInStudyPlan(semester, curricularYear, course, programme);
    }
}