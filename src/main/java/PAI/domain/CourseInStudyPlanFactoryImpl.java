package PAI.domain;

public interface CourseInStudyPlanFactoryImpl {
    CourseInStudyPlan newCourseInStudyPlan(int semester, int curricularYear, Course course, Programme programme) throws Exception;
}
