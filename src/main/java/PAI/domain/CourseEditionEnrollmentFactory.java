package PAI.domain;

import java.time.LocalDate;

public class CourseEditionEnrollmentFactory {

    public CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition, LocalDate enrollmentDate) {
        return new CourseEditionEnrollment(student, courseEdition, enrollmentDate);
    }
}
