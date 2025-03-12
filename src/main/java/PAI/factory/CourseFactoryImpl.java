package PAI.factory;

import PAI.domain.Course;

public class CourseFactoryImpl implements CourseFactory {

    public Course createCourse(String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {
        return new Course(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);
    }
}
