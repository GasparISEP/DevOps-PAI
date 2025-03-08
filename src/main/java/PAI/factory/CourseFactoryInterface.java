package PAI.factory;

import PAI.domain.Course;

public interface CourseFactoryInterface {
    public Course createCourse(String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception;
}



