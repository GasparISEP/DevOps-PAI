package PAI.domain;

public class CourseFactory {

    public Course createCourse(String courseName, String acronym, double quantityCreditsECTS, int durationCourseInSemester) throws Exception {
        return new Course(courseName, acronym, quantityCreditsECTS, durationCourseInSemester);
    }
}
