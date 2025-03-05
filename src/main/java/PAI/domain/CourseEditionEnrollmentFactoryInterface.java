package PAI.domain;

import java.time.LocalDate;

public interface CourseEditionEnrollmentFactoryInterface {

    CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition, LocalDate enrollmentDate);
}
