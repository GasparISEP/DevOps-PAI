package PAI.domain;

public class CourseInStudyPlanFactory {

    public CourseInStudyPlan newCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception {
        return new CourseInStudyPlan(semester, curricularYear, course, programme);
    }
}