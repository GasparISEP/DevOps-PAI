package PAI.domain;

import java.time.LocalDate;

public class CourseEditionEnrollmentFactory {

    public CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition) {
        return new CourseEditionEnrollment(student, courseEdition);
    }
}
