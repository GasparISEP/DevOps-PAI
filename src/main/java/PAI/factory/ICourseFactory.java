package PAI.factory;

import PAI.domain.Course;

public interface ICourseFactory {
    public Course createCourse(String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception;
}



