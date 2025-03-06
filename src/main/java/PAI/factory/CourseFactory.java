package PAI.factory;

import PAI.domain.Course;

public class CourseFactory implements CourseFactoryInterface {

    public Course createCourse(String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {
        return new Course(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);
    }
}
