package PAI.factory;

import PAI.domain.CourseEdition;
import PAI.domain.CourseEditionEnrollment;
import PAI.domain.Student;

import java.time.LocalDate;

public interface CourseEditionEnrollmentFactoryInterface {

    CourseEditionEnrollment createCourseEditionEnrollment(Student student, CourseEdition courseEdition);
}
